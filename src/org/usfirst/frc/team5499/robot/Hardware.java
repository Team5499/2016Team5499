package org.usfirst.frc.team5499.robot;

import org.usfirst.frc.team5499.robot.sensors.LightSensor;
import org.usfirst.frc.team5499.robot.sensors.Pot;
import org.usfirst.frc.team5499.robot.subsystems.Drive;
import org.usfirst.frc.team5499.robot.subsystems.Intake;
import org.usfirst.frc.team5499.robot.subsystems.Operator;
import org.usfirst.frc.team5499.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Hardware {
	CANTalon shooterBottomWheel;
	CANTalon shooterTopWheel;
	CANTalon shooterFeedWheel;
	CANTalon shooterArmMotor;
	CANTalon intakeArmMotor;
	CANTalon intakeRollerMotor;
	CANTalon driveLeft1;
	CANTalon driveLeft2;
	CANTalon driveRight1;
	CANTalon driveRight2;
	Encoder encLeft;
	Encoder encRight;
	DoubleSolenoid shiftLeft;
	DoubleSolenoid shiftRight;
	
	Pot shooterArmPot; 
	LightSensor shooterBottomSensor;
	LightSensor shooterTopSensor;
	Joystick leftStick;
	Joystick rightStick;
	Joystick xBoxController;
	public Shooter shooter;
	public Operator operatorStation;
	public Intake intake;
	public Drive drive;
	public PowerDistributionPanel pdp;
	
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
		
		pdp = new PowerDistributionPanel(12);
		
		leftStick = new Joystick(Reference.leftStickPort);
		rightStick = new Joystick(Reference.rightStickPort);
		xBoxController = new Joystick(Reference.xBoxController);
		
		shooter = new Shooter("shooter", shooterBottomWheel, shooterTopWheel,
				shooterFeedWheel, shooterArmMotor, shooterBottomSensor, shooterTopSensor, shooterArmPot);
		operatorStation = new Operator(leftStick, rightStick, xBoxController);
		intake = new Intake(intakeArmMotor, intakeRollerMotor);
		drive = new Drive(driveLeft1, driveLeft2, driveRight1, driveRight2, encLeft, encRight, shiftLeft, shiftRight);
	}
}
