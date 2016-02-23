package org.usfirst.frc.team5499.robot.commands;

public class ShootPrepRoutine extends Routine{
	
	Commands.ShotRequest shotRequest;
	
	public ShootPrepRoutine(Commands.ShotRequest shotRequest){
		this.shotRequest = shotRequest;
	}
	@Override
	public RobotState update(Commands commands, RobotState state) {
		System.out.println(shotRequest);
		shooter.setShotSpeeds(shotRequest);
		shooter.setWheelsOn();
		System.out.println(shooter.wheelsOn);
		shooter.raiseArm();
		state.shooting = true;
		return state;
	}
	
}
