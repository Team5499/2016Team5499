package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.lib.util.Loopable;
import org.usfirst.frc.team5499.robot.Reference;
import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.commands.Commands;
import org.usfirst.frc.team5499.robot.controllers.FeedForwardOutput;
import org.usfirst.frc.team5499.robot.controllers.FeedForwardWithPID;
import org.usfirst.frc.team5499.robot.controllers.PIDBase;
import org.usfirst.frc.team5499.robot.sensors.LightSensor;
import org.usfirst.frc.team5499.robot.sensors.Pot;
import org.usfirst.frc.team5499.robot.subsystems.OI.StickEnum;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter implements Loopable{
	
	private CANTalon bottomFlyWheel;
	private String name;
	private CANTalon topFlyWheel;
	private CANTalon feedWheel;
	private LightSensor bottomWheelSensor;
	private CANTalon armPivot;
	private LightSensor topWheelSensor;
	public Pot armPivotPot;
	PIDBase armController;
	double currentArmSetpoint;
	double intakeSpeed;
	FeedForwardWithPID bottomWheelController;
	FeedForwardWithPID topWheelController;
	public boolean wheelsOn;
	public boolean intaking;

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
		this.bottomWheelSensor.setInverted();
		this.armPivotPot = armPivotPot;
		this.armController = new PIDBase(Reference.shooterArmPGain, Reference.shooterArmIGain, 
				Reference.shooterArmDGain, Reference.shooterWheelILimit, Reference.shooterArmMaxOut, armPivotPot, 5);
		this.bottomWheelController = new FeedForwardWithPID(Reference.shooterWheelPGainBot, Reference.shooterWheelIGainBot, 
				Reference.shooterWheelDGainBot, Reference.shooterWheelKV,
				bottomWheelSensor, new FeedForwardOutput(), Reference.shooterWheelMaxBot);
//		this.bottomWheelController = new PIDController(Reference.shooterWheelPGain, Reference.shooterWheelIGain, Reference.shooterWheelDGain, bottomWheelSensor, bottomFlyWheel);
		this.topWheelController = new FeedForwardWithPID(Reference.shooterWheelPGainTop, Reference.shooterWheelIGainTop, 
				Reference.shooterWheelDGainTop, Reference.shooterWheelKV,
				topWheelSensor, new FeedForwardOutput(), Reference.shooterWheelMaxTop);
		
		topWheelController.enable();
		bottomWheelController.enable();
		topWheelController.setContinuous(); 
		bottomWheelController.setContinuous();
		System.out.println(topWheelController.isEnabled());
		//currentArmSetpoint = 0; //cornershot 62.5 //battershot16.5
		//this.armController.setSetpoint(currentArmSetpoint);
		this.intakeSpeed = .8;
		this.wheelsOn = false;
		this.intaking =false;
//		this.topFlyWheel.setPID(Reference.shooterWheelPGain, Reference.shooterWheelIGain, Reference.shooterWheelDGain);
//		this.topFlyWheel.setControlMode(2);
//		this.topFlyWheel
	}
	
	
	@Override
	public void update() {
		bottomWheelSensor.update();
		topWheelSensor.update();
		//bottomWheelController.setSetpoint(-1 * Reference.topWheelBatterSpeed);// / Reference.shooterWheelMaxBot);
		//topWheelController.setSetpoint(Reference.bottomWheelBatterSpeed);// / Reference.shooterWheelMaxTop);
		SmartDashboard.putNumber("Bottom Speed", bottomWheelSensor.getRate());
		SmartDashboard.putNumber("Top Speed", topWheelSensor.getRate());
		//		System.out.println("Control Loop Output: " + armController.getOutput());
//		System.out.println("Bottom Wheel: " + bottomWheelSensor.getRate());
//		System.out.println("Top Wheel: " + topWheelSensor.getRate());
	//	System.out.println(armPivotPot.getInput());
		
//		topFlyWheel.set(-1 * Robot.hardware.operatorStation.getStickAxis(StickEnum.XBOX, Reference.shooterTopAxis));
//		//topFlyWheel.set(1);
//		bottomFlyWheel.set(Robot.hardware.operatorStation.getStickAxis(StickEnum.XBOX, Reference.shooterBottomAxis));
//		if(Robot.hardware.operatorStation.getButton(StickEnum.XBOX, Reference.shooterOutTakeButton)){
//			feedWheel.set(Reference.shooterFeedSpeed);
//		}else if(Robot.hardware.operatorStation.getButton(StickEnum.XBOX, Reference.shooterInTakeButton)){
//			feedWheel.set(-1 * Reference.shooterFeedSpeed);
//		}else{
//			feedWheel.set(0);
//		}
//		armPivot.set(Robot.hardware.operatorStation.getStickAxis(StickEnum.XBOX, Reference.shooterArmUpAxis));
//		armPivot.set(-1 * Robot.hardware.operatorStation.getStickAxis(StickEnum.XBOX, Reference.shooterArmDownAxis));
//		if(Robot.hardware.operatorStation.getButton(StickEnum.OPERATOR, Reference.shooterArmControlButton)){
//			armController.update();
//			armPivot.set(-1 * armController.getOutput());
//		}else{
//			armPivot.set(0);
//		}
//		if(Robot.hardware.operatorStation.getButton(StickEnum.OPERATOR, Reference.shooterFeedWheelButton)){
//			feedWheel.set(Reference.shooterFeedSpeed);
//		}else{
//			feedWheel.set(0);
//		}
		armController.update();
		if(currentArmSetpoint > 2){
			armPivot.set(-1*armController.getOutput());
		}else{
			armPivot.set(0);
		}
		//		}else{
//			armPivot.set(0);
//		}
		if(wheelsOn){
			shootWheels();
		}else if(!intaking){
			stopWheels();
		}
	}
	public double getTopWheelSpeed(){
		return topWheelSensor.getRate();
	}
	
	public void runWheelsIn(){
		topFlyWheel.set(-1 * intakeSpeed);
		bottomFlyWheel.set(intakeSpeed);
		feedWheel.set(-1 * Reference.shooterFeedSpeed);
	}
	public void shootWheels(){
		topFlyWheel.set(topWheelController.getOutput());//Reference.shootSpeed);//topWheelController.getOutput());
		System.out.println(bottomWheelController.getOutput());
		bottomFlyWheel.set(-1 * topWheelController.getOutput());//-1 * Reference.shootSpeed);//bottomWheelController.getOutput());
		//Timer.delay(.2);
		//feedWheel.set(Reference.shooterFeedSpeed);
	}


	public void stopWheels() {
		topFlyWheel.set(0);
		bottomFlyWheel.set(0);
		feedWheel.set(0);
		wheelsOn = false;
	}

	public void setShotSpeeds(Commands.ShotRequest shotRequest){
		switch(shotRequest){
		case BATTER:
			topWheelController.setSetpoint(Reference.topWheelBatterSpeed);
			bottomWheelController.setSetpoint(Reference.bottomWheelBatterSpeed);
			currentArmSetpoint = Reference.armBatterAng;
			//armController.setSetpoint(setpoint2);
			break;
		case CLEAT:
			topWheelController.setSetpoint(Reference.topWheelCleatSpeed);
			bottomWheelController.setSetpoint(Reference.bottomWheelCleatSpeed);
			currentArmSetpoint = Reference.armCleatAng;
		//	armController.setSetpoint(setpoint);
			break;
		case AUTO:
			topWheelController.setSetpoint(Reference.autoShotSpeedTop);
			bottomWheelController.setSetpoint(Reference.autoShotSpeedBottom);
			currentArmSetpoint = Reference.autoShotAngle;
			//armController.setSetpoint(currentArmSetpoint);
			break;
		case OFF:
			stopWheels();
			lower();
			break;
		}
	}

	public void lower() {
		armPivot.set(0);
		currentArmSetpoint = 0;
		armController.setSetpoint(0);
	}


	public void raiseArm() {
		armController.setSetpoint(currentArmSetpoint);
		System.out.println("raising arm");
	}


	public void feed() {
		feedWheel.set(1);
	}


	public void setWheelsOn() {
		this.wheelsOn = true;
		
	}


	public void setIntaking() {
		this.intaking = true;
	}

}
