package org.usfirst.frc.team5499.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;

public class Operator {
	Joystick leftStick;
	Joystick rightStick;
	Joystick xBoxController;
	
	public enum StickEnum{
		LEFTSTICK, RIGHTSTICK, XBOX
	}
	
	public Operator(Joystick leftStick, Joystick rightStick, Joystick xBoxController){
		this.leftStick = leftStick;
		this.rightStick = rightStick;
		this.xBoxController = xBoxController;
	}
	public double getStickAxis(StickEnum stick, int axis){
		switch(stick){
			case LEFTSTICK:
				return leftStick.getRawAxis(axis);
			case RIGHTSTICK:
				return rightStick.getRawAxis(axis);
			case XBOX:
				return xBoxController.getRawAxis(axis);
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
			default:
				return false;		
		}
	}
}
