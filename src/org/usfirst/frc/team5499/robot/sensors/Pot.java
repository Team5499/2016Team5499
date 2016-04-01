package org.usfirst.frc.team5499.robot.sensors;

import org.usfirst.frc.team5499.robot.Reference;
import org.usfirst.frc.team5499.robot.controllers.PIDInput;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDSource;

public class Pot extends AnalogPotentiometer implements PIDInput, PIDSource {
	double lastValue;
	public Pot(int AIPort){
		super(AIPort, Reference.shooterArmPotScale, Reference.shooterArmPotZero);
	}
	
	@Override
	public double getInput() {
		// TODO Auto-generated method stub
		return this.get();
	}
	@Override
	public double pidGet(){
//		double out;
//		if(Math.abs(lastValue- this.get()) < 15){
//			System.out.println(this.get());
//			out = this.get();
//		}else{
//			out = lastValue;
//		}
//		lastValue = out;
		return this.get();
		
	}
}
