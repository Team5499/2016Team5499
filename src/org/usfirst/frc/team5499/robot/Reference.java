package org.usfirst.frc.team5499.robot;

public class Reference {
	
	//CAN IDs
	public static final int shooterTopWheelCANID = 3;
	public static final int shooterBottomWheelCANID = 1;
	public static final int shooterFeedWheelCANID = 2;
	public static final int driveRight1CANID = 4;
	public static final int driveRight2CANID = 5;
	public static final int driveLeft1CANID = 6;
	public static final int driveLeft2CANID = 7;
	public static final int shooterArmCANID = 8;

	public static final int intakeArmMotorCANID = 10;
	public static final int intakeRollerMotorCANID = 9;
	
	//AI DIO ports
	public static final int shooterTopLightDIOPort = 1;
	public static final int shooterBottomLightDIOPort = 0;
	public static final int shooterArmPotAIPort = 0;
	public static final int driveLeftEncoderA = 4;
	public static final int driveLeftEncoderB = 5;
	public static final int driveRightEncoderA = 2;
	public static final int driveRightEncoderB = 3;
	public static final int intakeEncoderA = 6;
	public static final int intakeEncoderB = 7;

	
	//Joysticks
	public static final int leftStickPort = 0;
	public static final int rightStickPort = 1;
	public static final int xBoxController = 2;
	public static final int shooterTopAxis = 1;
	public static final int shooterBottomAxis = 5;
	public static final int driveAxis = 1;
	public static final int shiftButton = 4;
//	public static final int shooterOutTakeButton = 1;
//	public static final int shooterInTakeButton = 2;
//	public static final int shooterArmUpAxis = 2;
//	public static final int shooterArmDownAxis = 3;
	public static int intakeArmAxis = 1;
	public static int intakeRollerInButton = 2;
	public static int intakeRollerOutButton = 3;
	public static final int shooterWheelsInButton = 9;
//	public static final int shooterWheelsOutButton = 6;
	
	//Random Constants
	public static final double shooterFeedSpeed = .8;
	public static final int lightSensorOnValue = 115;
	
	//Shooter Arm Constants
	public static double shooterArmPotScale = -269.5;//-272.72; //272.72
	public static double shooterArmPotZero = 270.5;//225;
	public static double shooterArmPGain = .06;
	public static double shooterArmIGain = .0001;
	public static double shooterArmDGain = .000001;
	public static double shooterArmILimit = 1000;
	public static double shooterArmMaxOut = 1;
	public static double armMaxAcc = 10;
	public static double armMaxVel = 200;
	public static double shootSpeed = 5000;

	public static int shiftLeftPCMPort1 = 0;
	public static int shiftLeftPCMPort2 = 1;
	public static int shiftRightPCMPort1 = 2;
	public static int shiftRightPCMPort2 = 3;

	public static double wheelCircum = Math.PI * (6.0/12.0);
	public static double encPulsesPerRev = 245;
	public static double distancePerPulseRight = .006414368;
	public static double distancePerPulseLeft = .006529546;

	public static int driveLeft1PDPPort = 12;
	public static int driveLeft2PDPPort = 13;
	public static int driveRight1PDPPort = 14;
	public static int driveRight2PDPPort = 15;
	public static int shooterFeedWheelButton = 1;
	public static int shooterArmControlButton = 3;
	public static double shooterWheelPGainTop = .000006; //.000006
	public static double shooterWheelIGainTop = 0;
	public static double shooterWheelDGainTop = 0;
	public static double shooterWheelPGainBot = .00005;
	public static double shooterWheelIGainBot = 0;
	public static double shooterWheelDGainBot = 0;
	public static double shooterWheelKV = 0;
	public static double shooterWheelILimit = 1000;
	public static double shooterWheelMaxTop = 5300;
	public static double shooterWheelMaxBot = 4800;
	public static double topWheelBatterSpeed = 5100;
	public static double bottomWheelBatterSpeed = 5000;
	public static double topWheelCleatSpeed = 5100;
	public static double bottomWheelCleatSpeed = 5000;
	public static double armBatterAng = 18.5;
	public static double armCleatAng = 32.5;
	public static int wheelDriveAxis = 0;
	public static int throttleAxis = 1;
	public static int wheel = 0;
	public static int throttle = 1;
	public static int operatorStick = 2;
	public static int operatorPanel = 6;
	public static int shiftHighButton = 1;
	public static int shiftLowButton = 2;
	public static int shooterBatterShotButton = 8;
	public static int shooterCleatShotButton = 10;
	public static int shootButton = 1;
	public static int shooterCancelButton = 6; 
	public static int driveSwitchButton = 7;
	
	public static double autoShotAngle = 45;
	public static double autoShotSpeedBottom = 5100;
	public static double autoShotSpeedTop = 5100;
	

}
