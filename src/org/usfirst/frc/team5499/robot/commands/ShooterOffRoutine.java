package org.usfirst.frc.team5499.robot.commands;

public class ShooterOffRoutine extends Routine {

	@Override
	public RobotState update(Commands commands, RobotState state) {
		shooter.stopWheels();
		shooter.lower();
		return state;
	}

}
