package org.usfirst.frc.team5499.robot.auto;

import org.usfirst.frc.team5499.robot.commands.Commands;

public class Command {

	public enum commandType{
		CMD_NULL, CMD_WAIT, CMD_INTAKE, CMD_AFLIP, CMD_DRIVE, CMD_TURN, CMD_CHANGE_SHOT, CMD_SHOOT, CMD_SHIFT
	};
	public double encLeftDistance;
	public double encRightDistance;
	public double heading;
	public boolean intakestate;
	public boolean aflipstate;
	public Commands.ShotRequest shooterShotRequest;
	public double shooterTopSpeed;
	public double shooterBottomSpeed;
	public double timeout;
	public commandType type;
	public Commands.ShiftRequest shiftRequest; //true = high, false = low
	
	public Command(commandType type, double encLeftDistance, double encRightDistance, double heading,
			boolean intakestate, boolean aflipstate, Commands.ShotRequest shooterShotRequest, double shooterTopSpeed, 
			double shooterBottomSpeed, Commands.ShiftRequest shiftRequest, double timeout){
		this.type = type;
		this.encLeftDistance = encLeftDistance;
		this.encRightDistance = encRightDistance;
		this.heading = heading;
		this.intakestate = intakestate;
		this.aflipstate = aflipstate;
		this.shooterShotRequest = shooterShotRequest;
		this.shooterTopSpeed = shooterTopSpeed;
		this.shooterBottomSpeed = shooterBottomSpeed;
		this.shiftRequest = shiftRequest;
		this.timeout = timeout;
		
	}
	public Command(commandType type){
		this(type, 0.0,0.0,0.0, false, false, 0.0, 0.0, 0.0, true, 15.0);
	}
}
