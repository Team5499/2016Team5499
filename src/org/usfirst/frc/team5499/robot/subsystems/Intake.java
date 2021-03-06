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
		this.rollerSpeed = 1;
		this.enc = enc;
	}

	@Override
	public void update() {
		if(!Robot.hardware.operatorStation.getButton(StickEnum.OPERATOR, Reference.aFlipButton)){
			armMotor.set(Robot.hardware.operatorStation.getStickAxis(StickEnum.OPERATOR, Reference.intakeArmAxis) * 0.8);
		}
		if(Robot.hardware.operatorStation.getButton(StickEnum.OPERATOR, Reference.intakeRollerInButton)){
			this.rollerMotor.set(rollerSpeed);
		}else if(Robot.hardware.operatorStation.getButton(StickEnum.OPERATOR, Reference.intakeRollerOutButton)){
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
		Timer.delay(.75);
		armMotor.set(0);
	}

	public void lowerArm() {
		armMotor.set(-.6);
		Timer.delay(.75);
		armMotor.set(0);
		
	}

	public void rollerIn() {
		rollerMotor.set(rollerSpeed);
	}
	
	public void rollerOut(){
		rollerMotor.set(-1 * rollerSpeed);
	}
	
	public void rollerStop(){
		rollerMotor.set(0);
	}
}
