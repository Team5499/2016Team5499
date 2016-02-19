package org.usfirst.frc.team5499.robot.commands;

public class CommandManager {

	Routine currentRoutine;
	boolean done;
	RobotState state;
	
	public CommandManager(){
		state = new RobotState();
	}
	
	public void update(Commands commands) {
		if(commands.intakeRequest == Commands.IntakeRequest.UP){
			setCurrentRoutine(new IntakeUpRoutine());
		}
		this.state = currentRoutine.update(commands, this.state);
	}
	
	public boolean isFinished(){
		return done;
	}
	public void setCurrentRoutine(Routine r){
		currentRoutine = r;
	}
	
	
}
