package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.robot.Reference;
import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.commands.Commands;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
	Joystick leftStick;
	Joystick rightStick;
	Joystick xBoxController;
	Joystick wheel;
	Joystick throttle;
	
	public enum StickEnum{
		LEFTSTICK, RIGHTSTICK, XBOX, WHEEL, THROTTLE
	}
	
	public OI(Joystick leftStick, Joystick rightStick, Joystick xBoxController, Joystick wheel, Joystick throttle){
		this.leftStick = leftStick;
		this.rightStick = rightStick;
		this.xBoxController = xBoxController;
		this.wheel = wheel;
		this.throttle = throttle;
	}
	public double getStickAxis(StickEnum stick, int axis){
		switch(stick){
			case LEFTSTICK:
				return leftStick.getRawAxis(axis) * .875;
			case RIGHTSTICK:
				return rightStick.getRawAxis(axis) * .875;
			case XBOX:
				return xBoxController.getRawAxis(axis);
			case WHEEL:
				return wheel.getRawAxis(axis);
			case THROTTLE:
				return throttle.getRawAxis(axis) * .875;
			default:
				return 0;		
		}
	}
	public boolean getButton(StickEnum stick, int button){
		switch(stick){
			case LEFTSTICK:
				return leftStick.getRawButton(button);
			case RIGHTSTICK:
				return rightStick.getRawButton(button);
			case XBOX:
				return xBoxController.getRawButton(button);
			case WHEEL:
				return wheel.getRawButton(button);
			case THROTTLE:
				return throttle.getRawButton(button);
			default:
				return false;		
		}
	}
	public Commands getCommands(){
		Commands cmds = new Commands();
		if(Robot.hardware.operatorStation.getButton(StickEnum.XBOX, Reference.shiftHighButton)){
			cmds.shiftRequest = Commands.ShiftRequest.HIGH;
		}else if(Robot.hardware.operatorStation.getButton(StickEnum.XBOX, Reference.shiftLowButton)){
			cmds.shiftRequest = Commands.ShiftRequest.LOW;
		}else{
			cmds.shiftRequest = Commands.ShiftRequest.OFF;
		}
		//System.out.println(cmds.shiftRequest);
		return cmds;
	}
}
