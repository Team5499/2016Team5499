package org.usfirst.frc.team5499.robot;

import org.usfirst.frc.team5499.robot.sensors.LightSensor;
import org.usfirst.frc.team5499.robot.sensors.Pot;
import org.usfirst.frc.team5499.robot.subsystems.Intake;
import org.usfirst.frc.team5499.robot.subsystems.Operator;
import org.usfirst.frc.team5499.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;

public class Hardware {
	CANTalon shooterBottomWheel;
	CANTalon shooterTopWheel;
	CANTalon shooterFeedWheel;
	CANTalon shooterArmMotor;
	CANTalon intakeArmMotor;
	CANTalon intakeRollerMotor;
	Pot shooterArmPot; 
	LightSensor shooterBottomSensor;
	LightSensor shooterTopSensor;
	Joystick leftStick;
	Joystick rightStick;
	Joystick xBoxController;
	public Shooter shooter;
	public Operator operatorStation;
	public Intake intake;
	
	public Hardware(){
		
		shooterBottomWheel = new CANTalon(Reference.shooterBottomWheelCANID);
		shooterTopWheel = new CANTalon(Reference.shooterTopWheelCANID);
		shooterFeedWheel = new CANTalon(Reference.shooterFeedWheelCANID);
		shooterArmMotor = new CANTalon(Reference.shooterArmCANID);
		shooterArmPot = new Pot(Reference.shooterArmPotAIPort); 
		shooterBottomSensor = new LightSensor(Reference.shooterBottomLightDIOPort);
		shooterTopSensor = new LightSensor(Reference.shooterTopLightDIOPort);
		
		intakeArmMotor = new CANTalon(Reference.intakeArmMotorCANID);
		intakeRollerMotor = new CANTalon(Reference.intakeRollerMotorCANID);
		
		leftStick = new Joystick(Reference.leftStickPort);
		rightStick = new Joystick(Reference.rightStickPort);
		xBoxController = new Joystick(Reference.xBoxController);
		
		shooter = new Shooter("shooter", shooterBottomWheel, shooterTopWheel,
				shooterFeedWheel, shooterArmMotor, shooterBottomSensor, shooterTopSensor, shooterArmPot);
		operatorStation = new Operator(leftStick, rightStick, xBoxController);
		intake = new Intake(intakeArmMotor, intakeRollerMotor);
	}
}
