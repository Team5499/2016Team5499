package org.usfirst.frc.team5499.robot.commands;

public class IntakeLowerRoutine extends Routine {

	@Override
	public RobotState update(Commands commands, RobotState state) {
		if(state.intakeUp){
			intake.lowerArm();
			state.intakeUp = false;
		}
		intake.rollerRun();
		
		return state;
	}

}
