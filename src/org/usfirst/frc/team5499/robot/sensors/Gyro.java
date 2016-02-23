package org.usfirst.frc.team5499.robot.sensors;

import org.usfirst.frc.team5499.robot.controllers.PIDInput;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Gyro implements PIDInput {

	public ADXRS450_Gyro gyro;
	
	public Gyro(){
		gyro = new ADXRS450_Gyro();
	}
	
	@Override
	public double getInput() {
		// TODO Auto-generated method stub
		return gyro.getAngle();
	}

}
