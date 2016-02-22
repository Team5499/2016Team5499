package org.usfirst.frc.team5499.robot.commands;

public class ShiftRoutine extends Routine {
	
	Commands.ShiftRequest shiftRequest;
	public ShiftRoutine(Commands.ShiftRequest shiftRequest){
		this.shiftRequest = shiftRequest;
	}
	
	@Override
	public RobotState update(Commands commands, RobotState state) {
		if(state.shift != shiftRequest){
			drive.shift(shiftRequest);
			state.shift = shiftRequest;
		}
		return state;
	}

}
