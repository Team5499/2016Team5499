package org.usfirst.frc.team5499.robot.controllers;

import org.usfirst.frc.team5499.lib.util.Loopable;

import edu.wpi.first.wpilibj.Timer;

public class PIDBase implements Loopable {
	double kp;
	double ki;
	double kd;
	double iTerm;
	PIDInput inputSource;
	double output;
	double setpoint;
	double lasttime;
	double lasterror;
	double iLimit;
	double maxOut;
	
	public PIDBase(double pGain, double iGain, double dGain, double iLimit, double maxOut, PIDInput source){
		this.kp = pGain;
		this.ki = iGain;
		this.kd = dGain;
		this.iLimit = iLimit;
		this.inputSource = source;
		this.output = 0;
		this.maxOut = maxOut;
	}
	
	public double calculateOutput(double setPoint, double input, double dt){
		double pTerm = (setPoint - input);
		double dTerm = (pTerm - lasterror) / dt;
		iTerm += (setpoint - input) * dt;
		if(iTerm > iLimit){
			iTerm = 0;
		}
		lasterror = pTerm;
		//System.out.println("pTerm: " + pTerm);
		//System.out.println("dTerm: " + dTerm);
		return (kp * pTerm + ki * iTerm + kd * dTerm); /// maxOut;
	}
	
	@Override
	public void update(){
		double input = inputSource.getInput();
		//System.out.println("iTerm: " + iTerm);
		double dt = Timer.getFPGATimestamp() - lasttime;
		lasttime = Timer.getFPGATimestamp();
		output = calculateOutput(setpoint, input, dt); 
	}
	
	public void setSetpoint(double setPoint){
		this.setpoint = setPoint;
	}
	public double getOutput(){
//		if(this.output > .6){
//			this.output = .6;
//		}else if(this.output < -.6){
//			this.output = -.6;
//		}
//		System.out.println(output);
		return this.output;
	}
	
}
