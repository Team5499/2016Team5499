package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.lib.util.Loopable;
import org.usfirst.frc.team5499.robot.Reference;
import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.subsystems.OI.StickEnum;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Timer;

public class Aflip implements Loopable{

	CANTalon armMotor;
	
	public Aflip(CANTalon armMotor){
		this.armMotor = armMotor;
	}

	@Override
	public void update() {
		if(Robot.hardware.operatorStation.getButton(StickEnum.OPERATOR, Reference.aFlipButton)){
			armMotor.set(.5 * Robot.hardware.operatorStation.getStickAxis(StickEnum.OPERATOR, Reference.intakeArmAxis));
		}else{
			armMotor.set(0);
		}
	}
	

	public void raiseArm() {
		armMotor.set(.6);
		Timer.delay(.1);
		armMotor.set(0);
	}

	public void lowerArm() {
		armMotor.set(-.6);
		Timer.delay(.1);
		armMotor.set(0);
		
	}


}
