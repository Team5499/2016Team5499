package org.usfirst.frc.team5499.robot.auto;

public class Command {

	public enum commandType{
		CMD_NULL, CMD_WAIT, CMD_INTAKE, CMD_AFLIP, CMD_DRIVE, CMD_CHANGESHOTSETPOINT, CMD_SHOOT, CMD_SHIFT
	};
	public double encLeftDistance;
	public double encRightDistance;
	public double heading;
	public boolean intakestate;
	public boolean aflipstate;
	public double shooterAngSetpoint;
	public double shooterTopSpeed;
	public double shooterBottomSpeed;
	public double timeout;
	public commandType type;
	public boolean shiftState; //true = high, false = low
	
	public Command(commandType type, double encLeftDistance, double encRightDistance, double heading,
			boolean intakestate, boolean aflipstate, double shooterAngSetpoint, double shooterTopSpeed, 
			double shooterBottomSpeed, boolean shiftState, double timeout){
		this.type = type;
		this.encLeftDistance = encLeftDistance;
		this.encRightDistance = encRightDistance;
		this.heading = heading;
		this.intakestate = intakestate;
		this.aflipstate = aflipstate;
		this.shooterAngSetpoint = shooterAngSetpoint;
		this.shooterTopSpeed = shooterTopSpeed;
		this.shooterBottomSpeed = shooterBottomSpeed;
		this.shiftState = shiftState;
		this.timeout = timeout;
		
	}
	public Command(commandType type){
		this(type, 0.0,0.0,0.0, false, false, 0.0, 0.0, 0.0, true, 15.0);
	}
}
