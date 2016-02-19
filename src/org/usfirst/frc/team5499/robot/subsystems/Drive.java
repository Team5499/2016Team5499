package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.lib.util.Loopable;
import org.usfirst.frc.team5499.robot.Reference;
import org.usfirst.frc.team5499.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;

public class Drive implements Loopable {
	CANTalon motorLeft1;
	CANTalon motorLeft2;
	CANTalon motorRight1;
	CANTalon motorRight2;
	Encoder encLeft;
	Encoder encRight;
	DoubleSolenoid leftShift;
	DoubleSolenoid rightShift;
	
	
	public Drive(CANTalon motorLeft1, CANTalon motorLeft2, CANTalon motorRight1, CANTalon motorRight2, 
			Encoder encLeft, Encoder encRight, DoubleSolenoid leftShift, DoubleSolenoid rightShift){
		this.motorLeft1 = motorLeft1;
		this.motorLeft2 = motorLeft2;
		this.motorRight1 = motorRight1;
		this.motorRight2 = motorRight2;
		this.encLeft = encLeft;
		this.encRight = encRight;
		this.leftShift = leftShift;
		this.rightShift = rightShift;		
	}
	
	@Override
	public void update() {
		setMotors(Robot.hardware.operatorStation.leftStick.getRawAxis(Reference.driveAxis),
				Robot.hardware.operatorStation.rightStick.getRawAxis(Reference.driveAxis));
		if(Robot.hardware.operatorStation.leftStick.getRawButton(Reference.shiftButton)){
			shift(ShiftEnum.HIGH);
		}else if(Robot.hardware.operatorStation.rightStick.getRawButton(Reference.shiftButton)){
			shift(ShiftEnum.LOW);
		}else{
			shift(ShiftEnum.OFF);
		}
	}
	
	public void setMotors(double leftSpeed, double rightSpeed){
		motorLeft1.set(leftSpeed);
		motorLeft2.set(leftSpeed);
		motorRight1.set(rightSpeed);
		motorRight2.set(rightSpeed);
	}
	public static enum ShiftEnum{
		HIGH,
		LOW,
		OFF
	}
	
	public void shift(ShiftEnum e){
		switch(e){
		case HIGH:
			leftShift.set(DoubleSolenoid.Value.kForward);
			rightShift.set(DoubleSolenoid.Value.kForward);
		case LOW:
			leftShift.set(DoubleSolenoid.Value.kReverse);
			rightShift.set(DoubleSolenoid.Value.kReverse);
		case OFF:
			leftShift.set(DoubleSolenoid.Value.kOff);
			rightShift.set(DoubleSolenoid.Value.kOff);
		default:
			leftShift.set(DoubleSolenoid.Value.kOff);
			rightShift.set(DoubleSolenoid.Value.kOff);
		}
	}

}
