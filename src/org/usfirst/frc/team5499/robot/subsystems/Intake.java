package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.lib.util.Loopable;
import org.usfirst.frc.team5499.robot.Reference;
import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.subsystems.OI.StickEnum;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

public class Intake implements Loopable {
	CANTalon armMotor;
	CANTalon rollerMotor;
	double rollerSpeed;
	Encoder enc;
	
	public Intake(CANTalon armMotor, CANTalon rollerMotor, Encoder enc){
		this.armMotor = armMotor;
		this.rollerMotor = rollerMotor;
		this.rollerSpeed = .52;
		this.enc = enc;
	}

	@Override
	public void update() {
		armMotor.set(Robot.hardware.operatorStation.getStickAxis(StickEnum.XBOX, Reference.intakeArmAxis) * 0.8);
		if(Robot.hardware.operatorStation.getStickAxis(StickEnum.XBOX, Reference.intakeRollerInAxis) >= .3){
			this.rollerMotor.set(rollerSpeed);
		}else if(Robot.hardware.operatorStation.getButton(StickEnum.XBOX, Reference.intakeRollerOutButton)){
			this.rollerMotor.set(-rollerSpeed);
		}else{	
			this.rollerMotor.set(0);
		}
	}
	
	public void setRollerSpeed(double speed){
		this.rollerSpeed = speed;
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

	public void rollerRun() {
		rollerMotor.set(rollerSpeed);
	}
	
	public void rollerStop(){
		rollerMotor.set(0);
	}
}
