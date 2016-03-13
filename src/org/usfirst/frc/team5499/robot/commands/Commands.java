package org.usfirst.frc.team5499.robot.commands;

public class Commands {
	public static enum ShotRequest {
		BATTER,
		CLEAT, OFF, AUTO, SPY

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

	public static enum Shoot{
		ON,
		OFF,
		IN
	}
	
	public IntakeRequest intakeRequest;
	public ShiftRequest shiftRequest;
	public Shoot shootRequest;
	public ShotRequest shotPrepRequest;
	public Object shooterInRequest;
	
}
