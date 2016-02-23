package org.usfirst.frc.team5499.robot.sensors;

import org.usfirst.frc.team5499.robot.controllers.PIDInput;

import com.kauailabs.navx.frc.AHRS;

public class NavX implements PIDInput {

	AHRS ahrs;
	
	public NavX(AHRS ahrs){
		this.ahrs = ahrs;
	}
	
	@Override
	public double getInput() {
		// TODO Auto-generated method stub
		//System.out.println("gyro: " + ahrs.getRawGyroX());
		return ahrs.getRoll();
	}

}
