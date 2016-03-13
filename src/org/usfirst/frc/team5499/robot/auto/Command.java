package org.usfirst.frc.team5499.robot.auto;

public class Command {

	public enum commandType{
		CMD_NULL, CMD_WAIT, CMD_INTAKE, CMD_AFLIP, CMD_DRIVE, CMD_CHANGESHOTSETPOINT, CMD_SHOOT
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

}
