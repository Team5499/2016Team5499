package com.team1538.lib;

import org.usfirst.frc.team5499.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Utility;

public class CowDisplay {
	private byte m_UserState;
	private byte m_PrevUserState;
	private int m_UserStatePeriodicCount;
	private int m_UserPeriodicCount;
	private byte m_UserScrollCount;
	private boolean m_ButtonPressedOnce;
	
	public CowDisplay()
	{
		m_UserState = 0;
		m_PrevUserState = 0;
		m_UserStatePeriodicCount = 0;
		m_UserPeriodicCount = 0;
		m_UserScrollCount = 0;
		m_ButtonPressedOnce = false;
		
		Robot.hardware.mxpAlphaNumDisplay.SetBanner("Team 5499 BHS Robotics");
	}
	
	public void DisplayUpdateState() {
		switch(m_UserState) {
		case 0:
			if (m_PrevUserState != m_UserState)
			{
				m_PrevUserState = m_UserState;
				m_UserScrollCount = 0;
				Robot.hardware.mxpAlphaNumDisplay.SetBanner("Team 5499 BHS Robotics");
			} else {
				m_UserScrollCount++;
			}
			Robot.hardware.mxpAlphaNumDisplay.SetBannerPosition(m_UserScrollCount);
			Robot.hardware.mxpAlphaNumDisplay.DisplayBanner();
			break;
		case 1:
			m_UserScrollCount = 0;
			if (m_PrevUserState != m_UserState) {
				m_PrevUserState = m_UserState;
				Robot.hardware.mxpAlphaNumDisplay.SetBanner("Volt");
			} else {
				double voltage = DriverStation.getInstance().getBatteryVoltage();
				String msg = String.format("%.2f  ", voltage);
				Robot.hardware.mxpAlphaNumDisplay.SetBanner(msg);
			}
			Robot.hardware.mxpAlphaNumDisplay.SetBannerPosition(m_UserScrollCount);
			Robot.hardware.mxpAlphaNumDisplay.DisplayBanner();
			break;
		case 2:
			m_UserScrollCount = 0;
			if (m_PrevUserState != m_UserState) {
				m_PrevUserState = m_UserState;
				Robot.hardware.mxpAlphaNumDisplay.SetBanner("Gyro");
			} else {
				if (m_UserStatePeriodicCount == 50) {
					String msg = String.format("%.2f  ", Robot.hardware.drive.gyro.GetAngle());
					Robot.hardware.mxpAlphaNumDisplay.SetBanner(msg);
				}
			}
			Robot.hardware.mxpAlphaNumDisplay.SetBannerPosition(m_UserScrollCount);
			Robot.hardware.mxpAlphaNumDisplay.DisplayBanner();
			break;
		default:
			break;
		}
	}
	
	public void DisplayNextState() {
		switch (m_UserState)
		{
		case 0 :
			m_PrevUserState = 0;
			m_UserState = 1;
			break;
		case 1 :
			m_PrevUserState = 1;
			m_UserState = 2;
			break;
		case 2 :
			m_PrevUserState = 2;
			m_UserState = 0;
			break;
		default :
			break;
		}		
	}
	
	public void DisplayState(boolean user) {
		if (user)
		{
			m_UserStatePeriodicCount = 0;
			DisplayNextState();
			DisplayUpdateState();
		}
		else
		{
			if ((m_UserState == 0) && ((m_UserStatePeriodicCount % 10) == 0))
			{
				DisplayUpdateState();
			}
			else if ((m_UserStatePeriodicCount % 300) == 0)
			{
				m_UserStatePeriodicCount = 0;
				DisplayNextState();
				DisplayUpdateState();
			}
			else if ((m_UserStatePeriodicCount % 10) == 0)
			{
				DisplayUpdateState();
			}
		}	
	}
	
	public void DisplayPeriodic() {
		boolean userButtonPressed = Utility.getUserButton();
		boolean buttonValue = false;

		if (userButtonPressed && !m_ButtonPressedOnce)
		{
			buttonValue = true;
			m_ButtonPressedOnce = true;
		}
		else if(!userButtonPressed && m_ButtonPressedOnce)
		{
			m_ButtonPressedOnce = false;
		}

		m_UserPeriodicCount++;
		m_UserStatePeriodicCount++;

		DisplayState(buttonValue);
	}
}
