package org.usfirst.frc.team5499.robot.sensors;

import org.usfirst.frc.team5499.lib.util.Loopable;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Timer;

public class LightSensor implements Loopable{
	double lasttime;
	double dt;
	double dtsum;
	double rps;
	double countsum;
	protected int currentcount;
	Counter count;
	
	public LightSensor(int DIOPort){
		count = new Counter(DIOPort);
		count.setExternalDirectionMode();
		
		
	}
	
	@Override
	public void update() {
		dt = Timer.getFPGATimestamp()- lasttime;
		lasttime = Timer.getFPGATimestamp();
		dtsum += dt;
		currentcount = count.get();
		countsum += currentcount;
		rps = countsum / dtsum;
		count.reset();
		currentcount=0;
		if (dtsum > 1){
			dtsum = 0;	
			countsum=0;
		}
	}
	
	public double getRate(){
		return rps * 60;
	}
	
//	class Interrupt extends InterruptHandlerFunction<Object>{
//		
//		@Override
//		public void interruptFired(int interruptAssertedMask, Object param) {
//			currentcount++;
//			System.out.println("interrupt fired");
//		}
//		
//	}
}