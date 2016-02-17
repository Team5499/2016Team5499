package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.lib.util.Loopable;
import org.usfirst.frc.team5499.robot.Hardware;
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
		motorLeft1.set(Robot.hardware.operatorStation.leftStick.getRawAxis(Reference.driveAxis));
		motorLeft2.set(Robot.hardware.operatorStation.leftStick.getRawAxis(Reference.driveAxis));
		motorRight1.set(Robot.hardware.operatorStation.rightStick.getRawAxis(Reference.driveAxis));
		motorRight2.set(Robot.hardware.operatorStation.rightStick.getRawAxis(Reference.driveAxis));
		if(Robot.hardware.operatorStation.leftStick.getRawButton(Reference.shiftButton)){
			leftShift.set(DoubleSolenoid.Value.kForward);
			rightShift.set(DoubleSolenoid.Value.kForward);
		}else if(Robot.hardware.operatorStation.rightStick.getRawButton(Reference.shiftButton)){
			leftShift.set(DoubleSolenoid.Value.kReverse);
			rightShift.set(DoubleSolenoid.Value.kReverse);
		}else{
			leftShift.set(DoubleSolenoid.Value.kOff);
			rightShift.set(DoubleSolenoid.Value.kOff);
		}
	}

}
