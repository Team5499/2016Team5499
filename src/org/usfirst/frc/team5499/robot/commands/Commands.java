package org.usfirst.frc.team5499.robot.commands;

public class Commands {
	public static enum ShotRequest {
		BATTER,
		CORNER
	}

	public static enum IntakeRequest{
		INTAKE,
		LOWER,
		NONE
	}
	
	public static enum ShiftRequest{
		HIGH,
		LOW,
		OFF
	}
	
	public IntakeRequest intakeRequest;
	public ShiftRequest shiftRequest;
	
}
