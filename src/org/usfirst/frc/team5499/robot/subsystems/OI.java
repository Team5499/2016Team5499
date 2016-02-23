package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.robot.Reference;
import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.commands.Commands;
import org.usfirst.frc.team5499.robot.subsystems.OI.StickEnum;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
	Joystick operatorStick;
	Joystick wheel;
	Joystick throttle;
	public enum StickEnum{
		LEFTSTICK, RIGHTSTICK, OPERATORSTICK, WHEEL, THROTTLE, OPERATORPANEL
	}
	
	public OI(Joystick operatorStick, Joystick wheel, Joystick throttle){
		this.operatorStick = operatorStick;
		this.wheel = wheel;
		this.throttle = throttle;
	}
	public double getStickAxis(StickEnum stick, int axis){
		switch(stick){
			case OPERATORSTICK:
				return operatorStick.getRawAxis(axis);
			case WHEEL:
				return wheel.getRawAxis(axis);
			case THROTTLE:
				return throttle.getRawAxis(axis);
			default:
				return 0;		
		}
	}
	public boolean getButton(StickEnum stick, int button){
		switch(stick){
			case OPERATORSTICK:
				return operatorStick.getRawButton(button);
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
//		if(Robot.hardware.operatorStation.getButton(StickEnum.THROTTLE, Reference.shiftButton)){
//			cmds.shiftRequest = Commands.ShiftRequest.HIGH;
//		}else if(!Robot.hardware.operatorStation.getButton(StickEnum.THROTTLE, Reference.shiftButton)){
//			cmds.shiftRequest = Commands.ShiftRequest.LOW;
//		}else{
//			cmds.shiftRequest = Commands.ShiftRequest.OFF;
//		}
		if(Robot.hardware.operatorStation.getButton(StickEnum.OPERATORSTICK, Reference.shooterBatterShotButton)){
			cmds.shotPrepRequest = Commands.ShotRequest.BATTER;
		}else if(Robot.hardware.operatorStation.getButton(StickEnum.OPERATORSTICK, Reference.shooterCleatShotButton)){
			cmds.shotPrepRequest = Commands.ShotRequest.CLEAT;
		}
		if(Robot.hardware.operatorStation.getButton(StickEnum.OPERATORSTICK, Reference.shootButton)){
			cmds.shootRequest = Commands.Shoot.ON;
		}else if(Robot.hardware.operatorStation.getButton(StickEnum.OPERATORSTICK, Reference.shooterWheelsInButton)){
			cmds.shootRequest = Commands.Shoot.IN;
		}else{
			cmds.shootRequest = Commands.Shoot.OFF;
		}if(Robot.hardware.operatorStation.getButton(StickEnum.OPERATORSTICK, Reference.shooterCancelButton)){
			cmds.shotPrepRequest = Commands.ShotRequest.OFF;
		}if(Robot.hardware.operatorStation.getButton(StickEnum.WHEEL, Reference.driveSwitchButton)){
			Robot.hardware.drive.setInverted(true);
		}else{
			Robot.hardware.drive.setInverted(false);
		}
		//System.out.println(cmds.shootRequest);
		//System.out.println(cmds.shiftRequest);
		return cmds;
	}
}
