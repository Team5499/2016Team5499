package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.lib.util.Loopable;
import org.usfirst.frc.team5499.robot.Reference;
import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.subsystems.Operator.StickEnum;

import edu.wpi.first.wpilibj.CANTalon;

public class Intake implements Loopable {
	CANTalon armMotor;
	CANTalon rollerMotor;
	double rollerSpeed;
	
	public Intake(CANTalon armMotor, CANTalon rollerMotor){
		this.armMotor = armMotor;
		this.rollerMotor = rollerMotor;
	}

	@Override
	public void update() {
		armMotor.set(Robot.hardware.operatorStation.getStickAxis(StickEnum.XBOX, Reference.intakeArmAxis) * 0.8);
		if(Robot.hardware.operatorStation.getButton(StickEnum.XBOX, Reference.intakeRollerInButton)){
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
}
