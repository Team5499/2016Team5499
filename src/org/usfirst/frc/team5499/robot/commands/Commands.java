package org.usfirst.frc.team5499.robot.commands;

public class Commands {
	public static enum ShotRequest {
		BATTER,
<<<<<<< HEAD
		CLEAT, OFF, AUTO
=======
		CLEAT
>>>>>>> 79677f8f981f162b3f65709c115ae6ffca847d5e
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
