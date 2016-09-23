package org.usfirst.frc.team5499.robot.commands;

import edu.wpi.first.wpilibj.Timer;

public class ShootRoutine extends Routine{

	@Override
	public RobotState update(Commands commands, RobotState state) {
		// TODO Auto-generated method stub
		shooter.feed();
		Timer.delay(2);
		shooter.stopWheels();
		shooter.lower();
		state.shooting = false;
		return state;
	}

}
