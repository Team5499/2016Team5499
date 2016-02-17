package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.lib.util.Loopable;
import org.usfirst.frc.team5499.robot.Reference;
import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.controllers.PIDBase;
import org.usfirst.frc.team5499.robot.sensors.LightSensor;
import org.usfirst.frc.team5499.robot.sensors.Pot;
import org.usfirst.frc.team5499.robot.subsystems.Operator.StickEnum;

import edu.wpi.first.wpilibj.CANTalon;

public class Shooter implements Loopable{
	
	private CANTalon bottomFlyWheel;
	private String name;
	private CANTalon topFlyWheel;
	private CANTalon feedWheel;
	private LightSensor bottomWheelSensor;
	private CANTalon armPivot;
	private LightSensor topWheelSensor;
	private Pot armPivotPot;
	PIDBase armController;
	double currentsetpoint;


	public Shooter(String name, CANTalon bottomFlyWheel, CANTalon topFlyWheel,
			CANTalon feedWheel, CANTalon armPivot, LightSensor bottomWheelSensor, 
			LightSensor topWheelSensor, Pot armPivotPot){
		this.bottomFlyWheel = bottomFlyWheel;
		this.name = name;
		this.topFlyWheel = topFlyWheel;
		this.feedWheel = feedWheel;
		this.armPivot = armPivot;
		this.bottomWheelSensor = bottomWheelSensor;
		this.topWheelSensor = topWheelSensor;
		this.armPivotPot = armPivotPot;
		this.armController = new PIDBase(Reference.shooterArmPGain, Reference.shooterArmIGain, Reference.shooterArmDGain, armPivotPot);
		currentsetpoint = 45;
		this.armController.setSetpoint(currentsetpoint);
	}
	
	
	@Override
	public void update() {
		bottomWheelSensor.update();
		topWheelSensor.update();

//		System.out.println("Control Loop Output: " + armController.getOutput());
//		System.out.println("Bottom Wheel: " + bottomWheelSensor.getRate());
//		System.out.println("Top Wheel: " + topWheelSensor.getRate());
		System.out.println(armPivotPot.getInput());
		
		topFlyWheel.set(-1 * Robot.hardware.operatorStation.getStickAxis(StickEnum.XBOX, Reference.shooterTopAxis));
		//topFlyWheel.set(1);
		bottomFlyWheel.set(Robot.hardware.operatorStation.getStickAxis(StickEnum.XBOX, Reference.shooterBottomAxis));
		if(Robot.hardware.operatorStation.getButton(StickEnum.XBOX, Reference.shooterOutTakeButton)){
			feedWheel.set(Reference.shooterFeedSpeed);
		}else if(Robot.hardware.operatorStation.getButton(StickEnum.XBOX, Reference.shooterInTakeButton)){
			feedWheel.set(-1 * Reference.shooterFeedSpeed);
		}else{
			feedWheel.set(0);
		}
//		armPivot.set(Robot.hardware.operatorStation.getStickAxis(StickEnum.XBOX, Reference.shooterArmUpAxis));
//		armPivot.set(-1 * Robot.hardware.operatorStation.getStickAxis(StickEnum.XBOX, Reference.shooterArmDownAxis));
		if(Robot.hardware.operatorStation.getButton(StickEnum.XBOX, 3)){
			armController.update();
			armPivot.set(-1 * armController.getOutput());
		}else{
			armPivot.set(0);
		}
		if(Robot.hardware.operatorStation.getButton(StickEnum.XBOX, 4)){
			armController.setSetpoint(20);
		}
	}
	public double getTopWheelSpeed(){
		return topWheelSensor.getRate();
	}

}
