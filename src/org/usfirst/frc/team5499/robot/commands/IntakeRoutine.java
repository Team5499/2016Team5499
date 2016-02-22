package org.usfirst.frc.team5499.robot.commands;

public class IntakeRoutine extends Routine {

	@Override
	public RobotState update(Commands commands, RobotState state) {
		// TODO Auto-generated method stub
		shooter.runWheelsIn();
		if(state.shooterUp){
			shooter.lower();
			state.shooterUp = false;
		}
		if(!state.intakeUp){
			intake.raiseArm();
			state.intakeUp = true;
		}
		shooter.stopWheels();

		return state;
	}

}
