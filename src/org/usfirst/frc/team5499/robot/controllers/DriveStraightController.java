package org.usfirst.frc.team5499.robot.controllers;

import org.usfirst.frc.team5499.lib.util.Loopable;
import org.usfirst.frc.team5499.robot.sensors.Gyro;

import com.team254.lib.trajectory.TrajectoryFollower;

import edu.wpi.first.wpilibj.Encoder;

public class DriveStraightController extends PIDBase implements Loopable{
	TrajectoryFollower left;
	TrajectoryFollower right;
	Encoder encLeft;
	Encoder encRight;
	double leftOutput;
	double rightOutput;
	boolean off;
	boolean splineOff;
	
	public DriveStraightController(Gyro source, TrajectoryFollower left, TrajectoryFollower right, Encoder encLeft, Encoder encRight) {
		super(.1, 0, .001, 1000, 1, source, 5);
		this.left = left;
		this.right = right;
		this.encLeft = encLeft;
		this.encRight = encRight;
		this.off = false;
	}
	
	@Override
	public void update(){
		super.update();
		double leftSet = left.calculate(encLeft.getDistance());
		double rightSet = right.calculate(encRight.getDistance());
		double gyrochange = super.getOutput();
		
		if(splineOff){
			leftSet = 0;
			rightSet = 0;
		}
		this.leftOutput = leftSet - gyrochange / 2;
		this.rightOutput = rightSet + gyrochange/ 2;
		System.out.println(leftOutput);
		System.out.println(rightOutput);
		if(off){
			this.leftOutput = leftSet / 10;
			this.rightOutput = rightSet / 10;
		}
		
	}
	public double getOutputLeft(){
		return this.leftOutput;
	}public double getOutputRight(){
		return this.rightOutput;
	}public void setSetpoint(double setpoint){
		super.setSetpoint(setpoint);
	}public void turnOff(){
		off = true;
	}public void turnOn(){
		off = false;
	}

	public void turnSplineOff() {
		splineOff = true;
		
	}
	
	
}
