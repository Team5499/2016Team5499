package org.usfirst.frc.team5499.robot;

public class Reference {
	
	//CAN IDs
	public static final int shooterTopWheelCANID = 1;
	public static final int shooterBottomWheelCANID = 2;
	public static final int shooterFeedWheelCANID = 3;
	public static final int driveRight1CANID = 4;
	public static final int driveRight2CANID = 5;
	public static final int driveLeft1CANID = 6;
	public static final int driveLeft2CANID = 7;
	public static final int shooterArmCANID = 8;

	public static final int intakeArmMotorCANID = 9;
	public static final int intakeRollerMotorCANID = 10;
	
	//AI DIO ports
	public static final int shooterTopLightDIOPort = 0;
	public static final int shooterBottomLightDIOPort = 1;
	public static final int shooterArmPotAIPort = 0;
	public static final int driveLeftEncoderA = 2;
	public static final int driveLeftEncoderB = 3;
	public static final int driveRightEncoderA = 4;
	public static final int driveRightEncoderB = 5;

	
	//Joysticks
	public static final int leftStickPort = 0;
	public static final int rightStickPort = 1;
	public static final int xBoxController = 2;
	public static final int shooterTopAxis = 1;
	public static final int shooterBottomAxis = 5;
	public static final int driveAxis = 1;
	public static final int shiftButton = 1;
	public static final int shooterOutTakeButton = 1;
	public static final int shooterInTakeButton = 2;
	public static final int shooterArmUpAxis = 2;
	public static final int shooterArmDownAxis = 3;
	public static int intakeArmAxis = 4;
	public static int intakeRollerInButton = 6;
	public static int intakeRollerOutButton = 7;
	
	//Random Constants
	public static final double shooterFeedSpeed = .8;
	public static final int lightSensorOnValue = 115;
	
	//Shooter Arm Constants
	public static double shooterArmPotScale = 272.72;
	public static double shooterArmPotZero = -80;
	public static double shooterArmPGain = .08;
	public static double shooterArmIGain = .0001;
	public static double shooterArmDGain = .000001;
	public static double armMaxAcc = 10;
	public static double armMaxVel = 200;
	

}
