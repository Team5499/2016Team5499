package org.usfirst.frc.team5499.robot.sensors;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;

public class NavX {
	AHRS ahrs;
	public NavX(){
		ahrs = new AHRS(SPI.Port.kMXP);
	}
}
