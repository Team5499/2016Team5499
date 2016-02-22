package org.usfirst.frc.team5499.robot.sensors;

import org.usfirst.frc.team5499.robot.Reference;
import org.usfirst.frc.team5499.robot.controllers.PIDInput;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class Pot extends AnalogPotentiometer implements PIDInput {
	
	public Pot(int AIPort){
		super(AIPort, Reference.shooterArmPotScale, Reference.shooterArmPotZero);
	}
	
	@Override
	public double getInput() {
		// TODO Auto-generated method stub
		return this.get();
	}

}
