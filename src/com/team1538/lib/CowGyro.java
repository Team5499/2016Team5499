package com.team1538.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.usfirst.frc.team5499.robot.controllers.PIDInput;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;

public class CowGyro implements PIDInput {
	public enum StatusFlag {
		INVALID_DATA,
		VALID_DATA,
		SELF_TEST_DATA,
		RW_RESPONSE,
		STATUS_FLAG_ERROR	
	};
	
	public enum ErrorFlag {
		GENERATED_FAULTS,
		CONTINUOUS_SELFTEST_FAILURE,
		POWER_FAILURE,
		RESET_INITIALIZE_FAILURE,
		NONVOLATILE_MEMORY_FAULT,
		QUADRATURE_ERROR,
		PLL_FAILURE
	};

	private Thread m_Thread;
	private SPI m_SPI;
	private ArrayList<ErrorFlag> m_ALL_ERRORS = new ArrayList<>(Arrays.asList(
		ErrorFlag.PLL_FAILURE,
		ErrorFlag.QUADRATURE_ERROR,
		ErrorFlag.NONVOLATILE_MEMORY_FAULT,
		ErrorFlag.RESET_INITIALIZE_FAILURE,
		ErrorFlag.POWER_FAILURE,
		ErrorFlag.CONTINUOUS_SELFTEST_FAILURE,
		ErrorFlag.GENERATED_FAULTS
	));
	
	static private final int SENSOR_DATA_CMD = 0x20000000;
	static private final int CHK_GENERATE_FAULTS_BIT = 0x03;
	static private final int K_READING_RATE = 1000;
	static private final int K_ZEROING_SAMPLES = 5 * K_READING_RATE;
	static private final int K_STARTUP_SAMPLES = 2 * K_READING_RATE;
	
	private int m_RemainingStartupCycles = K_STARTUP_SAMPLES;
	
	private double m_Angle = 0;
	private double m_LastTime = 0;
	private double m_VolatileRate = 0;
	private double m_ZeroBias = 0;
	private double[] m_ZeroRatesSamples = new double[K_ZEROING_SAMPLES];
	
	private boolean m_Calibrating = false;
	private boolean m_HasEnoughZeroingSamples = false;
	private boolean m_IsZeroed = false;
	
	private short m_CurrentIndex = 0;
	
	public CowGyro() {
		Timer.delay(0.25);
		
		m_SPI = new SPI(SPI.Port.kMXP);
		m_SPI.setClockRate(4000000);
		m_SPI.setChipSelectActiveLow();
		m_SPI.setClockActiveHigh();
		m_SPI.setMSBFirst();
		
		Timer.delay(2);
		m_Thread = new Thread(new CowGyroHandler());
	}
	
	private class CowGyroHandler implements Runnable {
		@Override
		public void run() {
			boolean initialized = false;
			while (!initialized) {
				try {
					initialized = InitializeGyro();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (!initialized) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			System.out.println(String.format("Gyro is initialized! Part ID: %x", ReadPartId()));
			
			while (true) {
				if (m_LastTime == 0) {
					m_LastTime = Timer.getFPGATimestamp();
				}
				
				try {
					Thread.sleep((long) ((1.0 / K_READING_RATE) * 1000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				int reading = GetReading();
				
				StatusFlag status = ExtractStatus(reading);
				
				ArrayList<ErrorFlag> errors = ExtractErrors(reading);
				
				if((status != StatusFlag.VALID_DATA) || !errors.isEmpty()) {
					System.err.println("Results: " + reading);
					System.err.println("Gyro read failed. Status: " + status + ". Errors: " + errors.size());
					for(ErrorFlag error : errors) {
						System.err.println("* " + error);
					}
					continue;
				}
				
				if (m_RemainingStartupCycles > 0) {
					m_RemainingStartupCycles--;
					continue;
				}
				
				double unbiasedAngleRate = ExtractAngleRate(reading);
				
				if (m_Calibrating) {
					m_ZeroRatesSamples[m_CurrentIndex] = unbiasedAngleRate;
					m_CurrentIndex++;
					if (m_CurrentIndex >= K_ZEROING_SAMPLES) {
						m_CurrentIndex = 0;
						m_HasEnoughZeroingSamples = true;
					}
				}
				
				if (m_IsZeroed) {
					double currentTime = Timer.getFPGATimestamp();
					double timeElapsed = currentTime - m_LastTime;
					m_LastTime = currentTime;
					
					m_VolatileRate = unbiasedAngleRate - m_ZeroBias;
					
					if(Math.abs(m_VolatileRate) < 0.0125) {
						m_VolatileRate = 0;
					}
					m_Angle += m_VolatileRate * timeElapsed;
				}
			}
		}
	}
	
	public float GetAngle() {
		return (float) m_Angle;
	}
	
	public double GetRate() {
		return m_VolatileRate;
	}
	
	private boolean InitializeGyro() throws InterruptedException {
		DoTransaction(SENSOR_DATA_CMD | CHK_GENERATE_FAULTS_BIT);
		
		Thread.sleep(50);
		
		DoTransaction(SENSOR_DATA_CMD);
		
		Thread.sleep(50);
		
		int selfCheckResult = DoTransaction(SENSOR_DATA_CMD);
		if (ExtractStatus(selfCheckResult) != StatusFlag.SELF_TEST_DATA) {
			System.err.println(String.format("Gyro not in self test: %x" , selfCheckResult));
			return false;
		}
		
		ArrayList<ErrorFlag> errors = ExtractErrors(selfCheckResult);
		if (!errors.equals(m_ALL_ERRORS)) {
			System.err.println(String.format("Gyro self-test didn't include all errors: %x", selfCheckResult));
			return false;
		}
		
		selfCheckResult = DoTransaction(SENSOR_DATA_CMD);
		if (ExtractStatus(selfCheckResult) != StatusFlag.SELF_TEST_DATA) {
			System.err.println(String.format("Gyro second self test read failed: %x" , selfCheckResult));
			return false;
		}
		
		return true;
	}
	
	private short DoRead(byte address) {
		int command = (0x8 << 28) | (address << 17);
		while (true) {
			int result = DoTransaction(command);
			if ((result & 0xEFE00000) != 0x4E000000) {
				System.err.println(String.format("Unexpected gyro read response: %x", result));
				continue;
			}
			return (short) ((result >> 5) & 0xFFFF);
		}
	}
	
	private double ExtractAngleRate(int result) {
		short reading = (short) ((result >> 10) & 0xFFFF);
		return ((double)(reading) / 80.0);
	}
	
	private short ReadPartId() {
		return DoRead((byte) 0x0C);
	}

	private int GetReading() {
		return DoTransaction(SENSOR_DATA_CMD);
	}
	
	private int DoTransaction(int command) {
		if (!IsOddParity(command & ~0x01)) {
			command |= 0x01;
		}
		
		byte[] commandArray = new byte[4];
		commandArray[0] = (byte) ((command >> 24) & 0xFF);
		commandArray[1] = (byte) ((command >> 16) & 0xFF);
		commandArray[2] = (byte) ((command >> 8) & 0xFF);
		commandArray[3] = (byte) (command & 0xFF);

		byte[] resultBuffer = new byte[4];
		
		int transactionSize = m_SPI.transaction(commandArray, resultBuffer, 4);
		if (transactionSize != 4) {
			System.err.println("Transaction failed with size: " + 4);
		}
		
		int result = 0;
		result = resultBuffer[3];
		result |= (resultBuffer[2] << 8);
		result |= (resultBuffer[1] << 16);
		result |= (resultBuffer[0] << 24);
		
		if (!IsOddParity(result)) {
			System.err.println("High bytes parity failues");
		}
		if (!IsOddParity(result)) {
			System.err.println("Whole word parity failues");
		}
		
		return result;
	}
	
	private boolean IsOddParity(int word) {
		boolean isOdd = false;
		for(int i = 0; i < 32; ++i) {
			if ((word & (1 << i)) != 0) {
				isOdd = !isOdd;
			}
		}
		return isOdd;
	}
	
	private StatusFlag ExtractStatus(int result) {
		int stBits = (result >> 26) & 0b11;
		switch (stBits) {
			case 0b00:
				return StatusFlag.INVALID_DATA;
			case 0b01:
				return StatusFlag.VALID_DATA;
			case 0b10:
				return StatusFlag.SELF_TEST_DATA;
			case 0b11:
				return StatusFlag.RW_RESPONSE;
			default:
				System.err.println("whiskey tango foxtrot");
		}
		return StatusFlag.STATUS_FLAG_ERROR;
	}
	
	private ArrayList<ErrorFlag> ExtractErrors(int result) {
		ArrayList<ErrorFlag> errors = new ArrayList<>();
		for (int i = 0; i < m_ALL_ERRORS.size(); ++i) {
			if ((result & (1 << i)) != 0) {
				errors.add(m_ALL_ERRORS.get(i));
			}
		}
		Collections.sort(errors);
		return errors;
	}
	
	private void Reset() {
		m_Angle = 0;
		m_CurrentIndex = 0;
		m_VolatileRate = 0;
		m_ZeroBias = 0;
	}
	
	public void BeginCalibration() {
		m_Calibrating = true;
		m_IsZeroed = false;
		Reset();
	}
	
	public void FinalizeCalibration() {
		m_Calibrating = false;
		m_IsZeroed = true;

		int index = m_CurrentIndex;

		if(m_HasEnoughZeroingSamples) {
			index = K_ZEROING_SAMPLES;
			m_HasEnoughZeroingSamples = false;
			System.out.println("Have enough zeroing samples!");
		} else {
			System.out.println("DO NOT have enough zeoring samples, got " + index);
		}

		// Average the samples in circular buffer
		for(int i = 0; i < index; ++i)
		{
			m_ZeroBias += (m_ZeroRatesSamples[i] / K_ZEROING_SAMPLES);
			m_ZeroRatesSamples[i] = 0;
		}

		m_LastTime = Timer.getFPGATimestamp();
	}

	@Override
	public double getInput() {
		return GetAngle();
	}
}
