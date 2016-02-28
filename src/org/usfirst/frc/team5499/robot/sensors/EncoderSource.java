package org.usfirst.frc.team5499.robot.sensors;

import org.usfirst.frc.team5499.robot.controllers.PIDInput;

import edu.wpi.first.wpilibj.Encoder;

public class EncoderSource implements PIDInput {
	Encoder enc;
	
	public EncoderSource(Encoder enc){
		this.enc = enc;
	}
	@Override
	public double getInput() {
		// TODO Auto-generated method stub
		return enc.getDistance();
	}

}
