package org.usfirst.frc.team5499.robot;

import org.usfirst.frc.team5499.robot.sensors.LightSensor;
import org.usfirst.frc.team5499.robot.sensors.Pot;
import org.usfirst.frc.team5499.robot.subsystems.Aflip;
import org.usfirst.frc.team5499.robot.subsystems.Drive;
import org.usfirst.frc.team5499.robot.subsystems.Intake;
import org.usfirst.frc.team5499.robot.subsystems.OI;
import org.usfirst.frc.team5499.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Hardware {
	CANTalon shooterBottomWheel;
	CANTalon shooterTopWheel;
	CANTalon shooterFeedWheel;
	CANTalon shooterArmMotor;
	CANTalon aFlipMotor;
	
	CANTalon intakeArmMotor;
	CANTalon intakeRollerMotor;
	Encoder intakeEncoder;
	
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
	Joystick wheel;
	Joystick throttle;
	public Shooter shooter;
	public OI operatorStation;
	public Intake intake;
	public Drive drive;
	public PowerDistributionPanel pdp;
	public Compressor c;
	public Aflip aflip;
	
	public Hardware(){

		aFlipMotor = new CANTalon(Reference.aFlipCANID);
		aflip = new Aflip(aFlipMotor);
		
		shooterBottomWheel = new CANTalon(Reference.shooterBottomWheelCANID);
		shooterTopWheel = new CANTalon(Reference.shooterTopWheelCANID);
		shooterFeedWheel = new CANTalon(Reference.shooterFeedWheelCANID);
		shooterArmMotor = new CANTalon(Reference.shooterArmCANID);
		shooterArmPot = new Pot(Reference.shooterArmPotAIPort); 
		shooterBottomSensor = new LightSensor(Reference.shooterBottomLightDIOPort, Reference.shooterWheelMaxBot);
		shooterTopSensor = new LightSensor(Reference.shooterTopLightDIOPort, Reference.shooterWheelMaxTop);
		
		driveLeft1 = new CANTalon(Reference.driveLeft1CANID);
		driveLeft2 = new CANTalon(Reference.driveLeft2CANID);
		driveRight1 = new CANTalon(Reference.driveRight1CANID);
		driveRight2 = new CANTalon(Reference.driveRight2CANID);
		encLeft = new Encoder(Reference.driveLeftEncoderA, Reference.driveLeftEncoderB, false, Encoder.EncodingType.k4X);
		encRight = new Encoder(Reference.driveRightEncoderA, Reference.driveRightEncoderB, false, Encoder.EncodingType.k4X);
		shiftLeft = new DoubleSolenoid(Reference.shiftLeftPCMPort1, Reference.shiftLeftPCMPort2);
		shiftRight = new DoubleSolenoid(Reference.shiftRightPCMPort1, Reference.shiftRightPCMPort2);
		
		c = new Compressor();
		
		intakeArmMotor = new CANTalon(Reference.intakeArmMotorCANID);
		intakeRollerMotor = new CANTalon(Reference.intakeRollerMotorCANID);
		intakeEncoder = new Encoder(Reference.intakeEncoderA, Reference.intakeEncoderB, false, Encoder.EncodingType.k4X);
		
		
		pdp = new PowerDistributionPanel(12);
		
		leftStick = new Joystick(Reference.leftStickPort);
		rightStick = new Joystick(Reference.rightStickPort);
		xBoxController = new Joystick(Reference.xBoxController);
		wheel = new Joystick(Reference.wheel);
		throttle = new Joystick(Reference.throttle);
		
		shooter = new Shooter("shooter", shooterBottomWheel, shooterTopWheel,
				shooterFeedWheel, shooterArmMotor, shooterBottomSensor, shooterTopSensor, shooterArmPot);
		operatorStation = new OI(leftStick, rightStick, xBoxController, wheel, throttle);
		intake = new Intake(intakeArmMotor, intakeRollerMotor, intakeEncoder);
		drive = new Drive(driveLeft1, driveLeft2, driveRight1, driveRight2, encLeft, encRight, shiftLeft, shiftRight);
	}
}
