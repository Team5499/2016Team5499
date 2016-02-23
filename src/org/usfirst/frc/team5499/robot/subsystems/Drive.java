package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.lib.util.Loopable;
import org.usfirst.frc.team5499.robot.Reference;
import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.commands.Commands;
import org.usfirst.frc.team5499.robot.commands.Commands.ShiftRequest;
import org.usfirst.frc.team5499.robot.subsystems.OI.StickEnum;

import com.kauailabs.navx.frc.AHRS;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.TrajectoryFollower;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;

public class Drive implements Loopable {
	CANTalon motorLeft1;
	CANTalon motorLeft2;
	CANTalon motorRight1;
	CANTalon motorRight2;
	Encoder encLeft;
	Encoder encRight;
	DoubleSolenoid leftShift;
	DoubleSolenoid rightShift;
	AHRS ahrs;
	TrajectoryFollower leftFollower;
	TrajectoryFollower rightFollower;
	static final double kp = .01;
	static final double ki = 0;
	static final double kd = .005;
	static final double kv = .8;
	static final double ka = .4;
	double lastShiftTime;
	double curTime;
	
	public Drive(CANTalon motorLeft1, CANTalon motorLeft2, CANTalon motorRight1, CANTalon motorRight2, 
			Encoder encLeft, Encoder encRight, DoubleSolenoid leftShift, DoubleSolenoid rightShift){
		this.motorLeft1 = motorLeft1;
		this.motorLeft2 = motorLeft2;
		this.motorRight1 = motorRight1;
		this.motorRight2 = motorRight2;
		this.encLeft = encLeft;
		this.encRight = encRight;
		this.encLeft.setDistancePerPulse(Reference.wheelCircum / Reference.encPulsesPerRev);
		this.leftShift = leftShift;
		this.rightShift = rightShift;
		this.leftFollower = new TrajectoryFollower();
		this.rightFollower = new TrajectoryFollower();
		this.leftFollower.configure(kp, ki, kd, kv, ka);
		this.rightFollower.configure(kp, ki, kd, kv, ka);
		this.lastShiftTime = 0;
		
		try{
			ahrs = new AHRS(SPI.Port.kMXP);
		}catch (RuntimeException ex){
			DriverStation.reportError("Error initializing Navx:" + ex.getMessage(), true);
		}
	}
	
	@Override
	public void update() {
		Robot.hardware.c.setClosedLoopControl(true);
		if(Robot.getState() == Robot.StateEnum.TELEOP){
//			System.out.println(Robot.hardware.pdp.getCurrent(Reference.driveLeft1PDPPort));
//			System.out.println(Robot.hardware.pdp.getCurrent(Reference.driveLeft2PDPPort));
//			System.out.println(Robot.hardware.pdp.getCurrent(Reference.driveRight1PDPPort));
//			System.out.println(Robot.hardware.pdp.getCurrent(Reference.driveRight2PDPPort));
			setMotorsWheel(Robot.hardware.operatorStation.getStickAxis(StickEnum.WHEEL, Reference.wheelDriveAxis),
					Robot.hardware.operatorStation.getStickAxis(StickEnum.THROTTLE, Reference.throttleAxis));
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
//				
//			}else{
//				shift(Commands.ShiftRequest.OFF);
//			}
		}else if(Robot.getState() == Robot.StateEnum.AUTO){
			if(leftFollower.isFinishedTrajectory()){
				encLeft.reset();
			}else if(rightFollower.isFinishedTrajectory()){
				encRight.reset();
			}else{
				setMotors(leftFollower.calculate(encLeft.getDistance()),
						rightFollower.calculate(encRight.getDistance()));
			}
			
		}
		
		
	}
	
	private void setMotorsWheel(double wheel, double throttle) {
		System.out.println(wheel);
		System.out.println(throttle);
		throttle = -1.5 * throttle;
		double left = (.5 + (wheel)) * throttle;
		double right = (.5 - (wheel)) * throttle;
		setMotors(left, right);
		
	}

	public void setMotors(double leftSpeed, double rightSpeed){
		motorLeft1.set(leftSpeed);
		motorLeft2.set(leftSpeed);
		motorRight1.set(-1 * rightSpeed);
		motorRight2.set(-1 * rightSpeed);
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
	
	public void setTrajectory(Trajectory.Pair pair){
		leftFollower.setTrajectory(pair.left);
		rightFollower.setTrajectory(pair.right);
	}

}
