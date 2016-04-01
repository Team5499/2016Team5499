package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.lib.util.Loopable;
import org.usfirst.frc.team5499.robot.Reference;
import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.commands.Commands;
import org.usfirst.frc.team5499.robot.commands.Commands.ShiftRequest;
import org.usfirst.frc.team5499.robot.controllers.PIDBase;
import org.usfirst.frc.team5499.robot.sensors.EncoderSource;
import org.usfirst.frc.team5499.robot.subsystems.OI.StickEnum;

import com.team1538.lib.CowGyro;
import com.team254.lib.trajectory.TrajectoryFollower;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

public class Drive implements Loopable {
	CANTalon motorLeft1;
	CANTalon motorLeft2;
	CANTalon motorRight1;
	CANTalon motorRight2;
	public Encoder encLeft;
	public Encoder encRight;
	DoubleSolenoid leftShift;
	DoubleSolenoid rightShift;

	public CowGyro gyro;
	public TrajectoryFollower leftFollower;
	public TrajectoryFollower rightFollower;
	static final double kp = 1.7;

	static final double ki = 0;
	static final double kd = .005;
	static final double kv = .8;
	static final double ka = .4;
	double lastShiftTime;
	double curTime;
	double inverted;
	//DriveStraightController angControl;
	public boolean trajFinished;
	public PIDBase leftPosControl;
	public PIDBase rightPosControl;
	public PIDBase angControl;
	public boolean posControl;
	private boolean control;
	public PIDBase angControlLow;
	public PIDBase leftPosControlLow;
	public boolean low;
	public PIDBase rightPosControlLow;
	
	public Drive(CANTalon motorLeft1, CANTalon motorLeft2, CANTalon motorRight1, CANTalon motorRight2, 
			Encoder encLeft, Encoder encRight, DoubleSolenoid leftShift, DoubleSolenoid rightShift, CowGyro gyro){
		trajFinished = false;
		this.inverted = -1;
		this.motorLeft1 = motorLeft1;
		this.motorLeft2 = motorLeft2;
		this.motorRight1 = motorRight1;
		this.motorRight2 = motorRight2;
		this.encLeft = encLeft;
		this.encRight = encRight;
		this.gyro = gyro;
		this.encLeft.setDistancePerPulse(Reference.distancePerPulseLeft);
		this.encRight.setDistancePerPulse(Reference.distancePerPulseRight);
		this.encRight.setReverseDirection(false);
		this.encLeft.setReverseDirection(true);
		this.leftShift = leftShift;
		this.rightShift = rightShift;
		this.leftPosControl = new PIDBase(1, 0, 0, 1000, 1, new EncoderSource(encLeft), 2);
		this.rightPosControl = new PIDBase(1, 0, 0, 1000, 1, new EncoderSource(encRight), 2);
		this.angControl = new PIDBase(.6, 0 ,0, 1000, 1, gyro, 2);
		this.leftPosControlLow = new PIDBase(1,0,0,1000, 1, new EncoderSource(encLeft), 1);
		this.rightPosControlLow = new PIDBase(1,0,0,1000, 1, new EncoderSource(encRight), 1);
		this.angControlLow = new PIDBase(.6, 0, 0, 1000, 1, gyro, 2);
		this.control = true;
		this.low = false;
//		this.leftFollower = new TrajectoryFollower();
//		this.rightFollower = new TrajectoryFollower();
//		this.leftFollower.configure(kp, ki, kd, kv, ka);
//		this.rightFollower.configure(kp, ki, kd, kv, ka);
		
		
		this.lastShiftTime = 0;
		
//		try{
//			ahrs = new AHRS(SPI.Port.kMXP);
//		}catch (RuntimeException ex){
//			DriverStation.reportError("Error initializing Navx:" + ex.getMessage(), true);
//		}
//		this.angControl = new DriveStraightController(gyro, leftFollower, rightFollower, encLeft, encRight);
//		this.angControl.setSetpoint(0);
		this.angControl.setSetpoint(0);
	}
	
	@Override
	public void update() {
		Robot.hardware.c.setClosedLoopControl(true);
		if(Robot.getState() == Robot.StateEnum.TELEOP){
			inverted = -1;
//			System.out.println(Robot.hardware.pdp.getCurrent(Reference.driveLeft1PDPPort));
//			System.out.println(Robot.hardware.pdp.getCurrent(Reference.driveLeft2PDPPort));
//			System.out.println(Robot.hardware.pdp.getCurrent(Reference.driveRight1PDPPort));
//			System.out.println(Robot.hardware.pdp.getCurrent(Reference.driveRight2PDPPort));
			double throttleVal = Robot.hardware.operatorStation.getStickAxis(StickEnum.THROTTLE, Reference.throttleAxis);
			if(Math.abs(throttleVal) > .05){
				setMotorsWheel(Robot.hardware.operatorStation.getStickAxis(StickEnum.WHEEL, Reference.wheelDriveAxis),
						throttleVal);
			}else{
				setMotorsWheel(Robot.hardware.operatorStation.getStickAxis(StickEnum.WHEEL, Reference.wheelDriveAxis), 0);
			}
//			setMotors(Robot.hardware.operatorStation.getStickAxis(StickEnum.LEFTSTICK,Reference.driveAxis),
//					Robot.hardware.operatorStation.getStickAxis(StickEnum.RIGHTSTICK, Reference.driveAxis));
			curTime = Timer.getFPGATimestamp();
//			if(Math.abs(lastShiftTime - curTime) > .125){
				if(Robot.hardware.operatorStation.getButton(StickEnum.THROTTLE, Reference.shiftButton)){
					shift(Commands.ShiftRequest.HIGH);
					System.out.println("attempt shift high");
					lastShiftTime = curTime;
				}else if(!Robot.hardware.operatorStation.getButton(StickEnum.THROTTLE, Reference.shiftButton)){
					shift(Commands.ShiftRequest.LOW);
					System.out.println("attempt shift low");
					lastShiftTime = curTime;
				}else{
					shift(Commands.ShiftRequest.OFF);
				}
			System.out.println("teleop drive");
//				
//			}else{
//				shift(Commands.ShiftRequest.OFF);
//			}
		}else if(Robot.getState() == Robot.StateEnum.AUTO){
//			leftPosControl.update();
//			rightPosControl.update();
//			angControl.update();
//			if(low){
//				leftPosControlLow.update();
//				rightPosControlLow.update();
//				angControlLow.update();
//			}
//			if(control){
////				if(!low){
//					double leftOutput = -1 * angControl.getOutput();
//					double rightOutput = angControl.getOutput();
//					if(posControl){
//						System.out.println("position control");
//						System.out.println("left distance: " + encLeft.getDistance());
//						leftOutput +=  leftPosControl.getOutput();
//						rightOutput += rightPosControl.getOutput();
//					}
//					setMotors(.6 * leftOutput, .6 * rightOutput);
////				}else{
////					double leftOutput = -1 * angControlLow.getOutput();
////					double rightOutput = angControlLow.getOutput();
////					if(posControl){
////						leftOutput += leftPosControlLow.getOutput();
////						rightOutput += rightPosControlLow.getOutput();
////					}
////					setMotors(leftOutput, rightOutput);
////				}
//			}else{
//				setMotors(0,0);
//			}
//				
////			System.out.println("left: " +leftOutput);
////			System.out.println("right: " + rightOutput);
//			System.out.println(gyro.getInput());

//			if(leftFollower.isFinishedTrajectory()&&rightFollower.isFinishedTrajectory()){
//				encLeft.reset();	
//				encRight.reset();
//				setMotors(0,0);
//			}else{
//				angControl.update();
//				if(leftFollower.isFinishedTrajectory() && rightFollower.isFinishedTrajectory()){
////					angControl.turnSplineOff();
////					System.out.println("spline finished");
////					angControl.turnOn();
////					angControl.setSetpoint(30);//leftFollower.getHeading() * 180 / Math.PI);
////					if(Math.abs(gyro.gyro.getAngle() - 30) < 10 ){
////						trajFinished = true;
////					}
//					trajFinished = true;
////					
//				}else{
//					//angControl.turnOff();
//					if(Math.abs(leftFollower.getHeading()) <.01){
//						angControl.setSetpoint(0);
//					}else{
//						angControl.turnOff();
//					}
//				}
//				System.out.println("Heading: " + leftFollower.getHeading());
				//ystem.out.println("Gyro: " + gyro.gyro.getAngle());
				//double leftSetpoint = angControl.getOutputLeft();
				//double rightSetpoint = angControl.getOutputRight();
//				double leftSetpoint = leftFollower.calculate(encLeft.getDistance());
//				double rightSetpoint = rightFollower.calculate(encRight.getDistance());
//				System.out.println("left: " + encLeft.getDistance());
//				System.out.println("right: " + encRight.getDistance());
				//System.out.println("leftSetpoint: " + leftSetpoint);
				//setMotors(leftSetpoint, rightSetpoint);
				//trajFinished = true;
			//}

			
				}
		
		
	}
	
	private void setMotorsWheel(double wheel, double throttle) {
		//System.out.println(wheel);
		//System.out.println(throttle);
//		throttle = -2 * throttle;
//		double left = (.5 + inverted * (wheel)) * throttle;
//		double right = (.5 - inverted * (wheel)) * throttle;
//		System.out.println("Left: " + left);
//		setMotors(left, right);
		if(Math.abs(throttle)>.01) {
			if(this.low) {
				wheel *= .3; //.3
			} else { 
				wheel *= .6; //.6
			}
		}else{
			wheel *= 2;
		}
		if(throttle < -.01){
			wheel *= -1;
		}	
		throttle *= -1;
			
		double left = throttle + wheel;
		double right = throttle - wheel;
		setMotors(left, right);
	}

	public void setMotors(double leftSpeed, double rightSpeed){
		motorLeft1.set(inverted * leftSpeed);
		motorLeft2.set(inverted * leftSpeed);
		motorRight1.set(-1 * inverted * rightSpeed);
		motorRight2.set(-1 * inverted * rightSpeed);
	}
	
	public void shift(ShiftRequest shiftRequest){
		switch(shiftRequest){
		case HIGH:
			leftShift.set(DoubleSolenoid.Value.kForward);
			rightShift.set(DoubleSolenoid.Value.kForward);
			System.out.println("shiftHigh");
			break;
		case LOW:
			leftShift.set(DoubleSolenoid.Value.kReverse);
			rightShift.set(DoubleSolenoid.Value.kReverse);
			System.out.println("shiftLow");
			break;
		case OFF:
			leftShift.set(DoubleSolenoid.Value.kOff);
			rightShift.set(DoubleSolenoid.Value.kOff);
			break;
		default:
			leftShift.set(DoubleSolenoid.Value.kOff);
			rightShift.set(DoubleSolenoid.Value.kOff);
		}
	}
	
//	public void setTrajectory(Trajectory.Pair pair){
//		leftFollower.setTrajectory(pair.left);
//		rightFollower.setTrajectory(pair.right);
//	}
	public void setInverted(boolean invert){
		this.inverted = invert ? -1:1;
	}

	public void controlOff() {
		this.control = false;
	}
	public void controlOn(){
		this.control = true;
	}

}
