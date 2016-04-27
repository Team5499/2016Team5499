package org.usfirst.frc.team5499.robot.commands;

import org.usfirst.frc.team5499.robot.Robot;

public class ShooterOffRoutine extends Routine {

	@Override
	public RobotState update(Commands commands, RobotState state) {
		shooter.stopWheels();
		shooter.lower();
		Robot.hardware.drive.visionControl = false;
		return state;
	}

}
