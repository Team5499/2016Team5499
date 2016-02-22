package org.usfirst.frc.team5499.robot.commands;

public class CommandManager {

	Routine currentRoutine;
	RobotState state;
	
	public CommandManager(){
		state = new RobotState();
	}
	
	public void update(Commands commands) {
		if(commands.intakeRequest == Commands.IntakeRequest.LOWER){
			setCurrentRoutine(new IntakeLowerRoutine());
		}else if(commands.intakeRequest == Commands.IntakeRequest.INTAKE){
			setCurrentRoutine(new IntakeRoutine());
		}else if(commands.shiftRequest == Commands.ShiftRequest.LOW){
			setCurrentRoutine(new ShiftRoutine(commands.shiftRequest));
			System.out.println("ShiftRequestLow");
		}else if(commands.shiftRequest == Commands.ShiftRequest.HIGH){
			setCurrentRoutine(new ShiftRoutine(commands.shiftRequest));
			System.out.println("ShiftRequestHigh");
		}else if(commands.shiftRequest == Commands.ShiftRequest.OFF){
			setCurrentRoutine(new ShiftRoutine(commands.shiftRequest));
		}
		if(currentRoutine!=null){
			this.state = currentRoutine.update(commands, this.state);
		}
	}
	
	public void setCurrentRoutine(Routine r){
		currentRoutine = r;
	}
	
	
}
