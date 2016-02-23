package org.usfirst.frc.team5499.robot.commands;

public class CommandManager {

	Routine currentRoutine;
	RobotState state;
	
	public CommandManager(){
		state = new RobotState();
	}
	
	public void update(Commands commands) {
		
		if(commands.shiftRequest == Commands.ShiftRequest.LOW){
			setCurrentRoutine(new ShiftRoutine(commands.shiftRequest));
			//System.out.println("ShiftRequestLow");
		}else if(commands.shiftRequest == Commands.ShiftRequest.HIGH){
			setCurrentRoutine(new ShiftRoutine(commands.shiftRequest));
			//System.out.println("ShiftRequestHigh");
		}else if(commands.shiftRequest == Commands.ShiftRequest.OFF){
			setCurrentRoutine(new ShiftRoutine(commands.shiftRequest));
		}else if(commands.shotPrepRequest == Commands.ShotRequest.BATTER){
			setCurrentRoutine(new ShootPrepRoutine(commands.shotPrepRequest));
		}else if(commands.shotPrepRequest == Commands.ShotRequest.CLEAT){
			setCurrentRoutine(new ShootPrepRoutine(commands.shotPrepRequest));
			//System.out.println("shooter cleat prep");
		}else if(commands.shotPrepRequest == Commands.ShotRequest.AUTO){
			setCurrentRoutine(new ShootPrepRoutine(commands.shotPrepRequest));
		}else if(commands.shotPrepRequest == Commands.ShotRequest.OFF && !state.shooting){
			setCurrentRoutine(new ShooterOffRoutine());
			//System.out.println("shooter off");
		}
		else if(commands.shootRequest == Commands.Shoot.ON){
			setCurrentRoutine(new ShootRoutine());
		}else if(commands.shootRequest == Commands.Shoot.IN){
			setCurrentRoutine(new ShootInRoutine());
		}
		else if(commands.shootRequest == Commands.Shoot.OFF && !state.shooting){
			setCurrentRoutine(new ShooterOffRoutine());
		}
		if(currentRoutine!=null){
			this.state = currentRoutine.update(commands, this.state);
		}
	}
	
	public void setCurrentRoutine(Routine r){
		currentRoutine = r;
	}
	
	
}
