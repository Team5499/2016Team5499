package org.usfirst.frc.team5499.robot.sensors;

import org.usfirst.frc.team5499.lib.util.Loopable;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class LightSensor implements Loopable, PIDSource{
	double lasttime;
	double dt;
	double dtsum;
	double rpm;
	double lastrpm;
	double countsum;
	protected int currentcount;
	Counter count;
	double maxSpeed;
	double invert;
	
	public LightSensor(int DIOPort, double maxSpeed){
		count = new Counter(DIOPort);
		count.setUpDownCounterMode();
		this.maxSpeed = maxSpeed;
		this.invert = 1;
		
	}
	
	@Override
	public void update() {
//		dt = Timer.getFPGATimestamp()- lasttime;
//		lasttime = Timer.getFPGATimestamp();
		rpm = 60 / (count.getPeriod());
		if(Math.abs(lastrpm - rpm)>2000){
			rpm = lastrpm;
		}
		lastrpm = rpm;
//		dtsum += dt;
//		countsum += currentcount;
		
//		if(dtsum > 3){
//			dtsum = 0;
//			countsum = 0;
//		
//		}
//		count.reset();
	}
	
	public double getRate(){
		return rpm;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return PIDSourceType.kRate;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return invert * getRate(); /// maxSpeed;
	}
}