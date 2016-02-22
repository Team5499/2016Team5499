package org.usfirst.frc.team5499.robot.controllers;

import edu.wpi.first.wpilibj.PIDOutput;

public class FeedForwardOutput implements PIDOutput {
	public double output;
	@Override
	public void pidWrite(double output) {
		this.output = output;
	}

}
