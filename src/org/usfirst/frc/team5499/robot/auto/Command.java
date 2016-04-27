package org.usfirst.frc.team5499.robot.auto;

import org.usfirst.frc.team5499.robot.commands.Commands;

public class Command {

	public enum commandType{
		CMD_NULL, CMD_WAIT, CMD_INTAKE, CMD_AFLIP, CMD_DRIVE, CMD_DRIVESTRAIGHT, 
		CMD_TURN, CMD_CHANGE_SHOT, CMD_SHOOT, CMD_SHIFT, CMD_INTAKEROLLER, CMD_SHOOTERDOWN, 
		CMD_TURNVISIONNOGYRO, CMD_TURNVISIONSNAP
	};
	public enum IntakeRollerState{
		IN, OUT, OFF
	}
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
	public IntakeRollerState intakeRollerState;
	
	public Command(commandType type, double encLeftDistance, double encRightDistance, double heading,
			boolean intakestate, boolean aflipstate, Commands.ShotRequest shooterShotRequest, double shooterTopSpeed, 
			double shooterBottomSpeed, Commands.ShiftRequest shiftRequest, double timeout, IntakeRollerState intakeRollerState){
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
		this.intakeRollerState = intakeRollerState;
	}
	public Command(commandType type){
		this(type, 0.0,0.0,0.0, false, false, Commands.ShotRequest.OFF, 0.0, 0.0, Commands.ShiftRequest.HIGH, 15.0, IntakeRollerState.OFF);
	}
	public Command(){
	}
}
