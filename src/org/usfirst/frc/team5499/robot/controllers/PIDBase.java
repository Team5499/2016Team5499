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
	double tolerance;
	boolean off;
	
	public PIDBase(double pGain, double iGain, double dGain, double iLimit, double maxOut, PIDInput source, double tolerance){
		this.kp = pGain;
		this.ki = iGain;
		this.kd = dGain;
		this.iLimit = iLimit;
		this.inputSource = source;
		this.output = 0;
		this.maxOut = maxOut;
		this.tolerance = tolerance;
	}
//	public PIDBase(double pGain, double iGain, double dGain, double iLimit, double maxOut, PIDInput source){
//		PIDBase(pGain, iGain, dGain, iLimit, maxOut, source, 1.0);
//	}
	
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
		if(!off){
			output = calculateOutput(setpoint, input, dt); 
		}else{
			output = .5;
		}
	}
	
	public void setSetpoint(double setPoint){
		this.setpoint = setPoint;
	}
	public double getOutput(){
		if(this.output > 1){
			this.output = 1;
		}else if(this.output < -1){
			this.output = -1;
		}
//		System.out.println(output);
		return this.output;
	}
	
	public boolean isFinished(){
		if(Math.abs(inputSource.getInput() - this.setpoint) < this.tolerance || this.off == true){
			return true;
		}else{
			return false;
		}
	}
	
	public void turnOff(boolean off){
		if(off){
			this.off = true;
		}else{
			this.off = false;
		}
	}
	
}
