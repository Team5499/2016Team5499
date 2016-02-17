package org.usfirst.frc.team5499.robot.controllers;

import org.usfirst.frc.team5499.lib.util.Loopable;

import edu.wpi.first.wpilibj.Timer;

public class PIDBase implements Loopable {
	double kp;
	double ki;
	double kd;
	double iTerm;
	PIDSource inputSource;
	double output;
	double setpoint;
	double lasttime;
	double lasterror;
	
	public PIDBase(double pGain, double iGain, double dGain, PIDSource source){
		this.kp = pGain;
		this.ki = iGain;
		this.kd = dGain;
		this.inputSource = source;
		this.output = 0;
	}
	
	public double calculateOutput(double setPoint, double input, double iTerm, double dt){
		double pTerm = (setPoint - input);
		double dTerm = (pTerm - lasterror) / dt;
		lasterror = pTerm;
		//System.out.println("pTerm: " + pTerm);
		//System.out.println("dTerm: " + dTerm);
		return kp * pTerm + ki * iTerm + kd * dTerm;
	}
	
	@Override
	public void update(){
		double input = inputSource.getInput();
		iTerm += setpoint - input;
		if(iTerm > 1000){
			iTerm = 0;
		}
		//System.out.println("iTerm: " + iTerm);
		double dt = Timer.getFPGATimestamp() - lasttime;
		lasttime = Timer.getFPGATimestamp();
		output = calculateOutput(setpoint, input, iTerm, dt); 
	}
	
	public void setSetpoint(double setPoint){
		this.setpoint = setPoint;
	}
	public double getOutput(){
		if(this.output >.6){
			this.output = .6;
		}else if(this.output < -.6){
			this.output = -.6;
		}
		return this.output;
	}
	
}
