package org.usfirst.frc.team5499.robot.controllers;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;

public class FeedForwardWithPID extends PIDController{

	double kv;
	FeedForwardOutput out;
	double maxSpeed;
	
	public FeedForwardWithPID(double pGain, double iGain, double dGain, double kv,
			PIDSource source, FeedForwardOutput output, double maxSpeed) {
		super(pGain, iGain, dGain, source, output);
		this.kv = kv;
		this.out = output;
		this.maxSpeed = maxSpeed;
	}
	
	public double getOutput(){
		return out.output;//.95 *( out.output + ((kv * super.getSetpoint()) / maxSpeed));
	}

}
