package org.usfirst.frc.team5499.robot.commands;

public class ShootInRoutine extends Routine {

	@Override
	public RobotState update(Commands commands, RobotState state) {
		shooter.runWheelsIn();
		shooter.setIntaking();
		return state;
	}

}
