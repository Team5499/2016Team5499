package org.usfirst.frc.team5499.robot.auto;

import java.util.ArrayDeque;

import org.usfirst.frc.team5499.lib.util.Loopable;
import org.usfirst.frc.team5499.robot.Hardware;
import org.usfirst.frc.team5499.robot.Reference;
import org.usfirst.frc.team5499.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public class AutoMode implements Loopable{
	
	ArrayDeque<Command> cmdSeq;
	Command currentCommand;
	Timer timer;
	
	public AutoMode(){
		timer = new Timer();
	}
	
	@Override
	public void update() {
		boolean result = false;
		switch(currentCommand.type){
			case CMD_NULL:
				// make robot do nothihng (kill all mtors)
				doNothing();
				break;
			case CMD_WAIT:
				// same here
				doNothing();
				break;
			case CMD_INTAKE:
				if(currentCommand.intakestate)
				{
					Robot.hardware.intake.lowerArm();
				} else {
					Robot.hardware.intake.raiseArm();
				}
				break;
			case CMD_AFLIP:
				if(currentCommand.aflipstate)
				{
					Robot.hardware.aflip.lowerArm();
				} else {
					Robot.hardware.aflip.raiseArm();
				}
				break;
			case CMD_DRIVE:
				double driveError = currentCommand.encLeftDistance - Robot.hardware.drive.encLeft.getDistance();
				double output = -1 * driveError * Reference.driveP; // james fill this in
				double turnError = currentCommand.heading - Robot.hardware.drive.gyro.getInput();
				double outputTurn = turnError * Reference.turnP; // james fill this in
				System.out.println("Goal: " + currentCommand.encLeftDistance);
				System.out.println("dist: " + Robot.hardware.drive.encLeft.getDistance());
				System.out.println("gyro: " + Robot.hardware.drive.gyro.getInput() + " / " + currentCommand.heading);
				System.out.println("outputTurn: " + outputTurn);
				if(output > .4){
					output = .4;
				}else if(output < -.4){
					output = -.4;
				}
				Robot.hardware.drive.setMotors(output - outputTurn, output + outputTurn);
				if(driveError > -1.0/12 && driveError < 1.0/12 && turnError > -1 && turnError < 1) {
					result = true;
				}
				break;
			case CMD_DRIVESTRAIGHT:
				driveError = currentCommand.encLeftDistance - Robot.hardware.drive.encLeft.getDistance();
				output = -1 * driveError * Reference.driveP; // james fill this in
				turnError = Robot.hardware.drive.encLeft.getDistance()- Robot.hardware.drive.encRight.getDistance();
				outputTurn = turnError * 0.3; // james fill this in
				if(output > .4){
					output = .4;
				}else if(output < -.4){
					output = -.4;
				}
				Robot.hardware.drive.setMotors(output - outputTurn, output + outputTurn);
				if(driveError > -1.0/12 && driveError < 1.0/12 && turnError > -2.0/12 && turnError < 2.0/12) {
					result = true;
				}
				break;
			case CMD_TURN:
				turnError = currentCommand.heading - Robot.hardware.drive.gyro.getInput();
				outputTurn = turnError * Reference.turnP; // james fill this in
				Robot.hardware.drive.setMotors(-outputTurn, outputTurn);
				System.out.println("Goal: " + currentCommand.encLeftDistance);
				System.out.println("dist: " + Robot.hardware.drive.encLeft.getDistance());
				System.out.println("gyro: " + Robot.hardware.drive.gyro.getInput() + " / " + currentCommand.heading);
				System.out.println("outputTurn: " + outputTurn);
				if(turnError > -1 && turnError < 1) {
					result = true;
				}
				break;
			case CMD_CHANGE_SHOT:
				Robot.hardware.shooter.setShotSpeeds(currentCommand.shooterShotRequest);
				Robot.hardware.shooter.raiseArm();
				Robot.hardware.shooter.setWheelsOn();
				System.out.println("Changing shot");
				break;
			case CMD_SHOOT:
				Robot.hardware.shooter.setShotSpeeds(currentCommand.shooterShotRequest);
				Robot.hardware.shooter.feed();
				break;
			case CMD_SHIFT:
				Robot.hardware.drive.shift(currentCommand.shiftRequest);
				result = true;
				break;
		}
		if(result || timer.get() > currentCommand.timeout) {
			if(currentCommand.type != Command.commandType.CMD_NULL) {
				if(cmdSeq.size() == 0)
				{
					currentCommand = new Command();
					currentCommand.type = Command.commandType.CMD_NULL;
				} else {
					currentCommand = cmdSeq.getFirst();
					cmdSeq.removeFirst();
					timer.reset();
					Robot.hardware.drive.encLeft.reset();
					Robot.hardware.drive.encRight.reset();
				}
			}
		}
	}
	
	public void setCommandSequence(ArrayDeque<Command> cmdSeq){
		this.cmdSeq = cmdSeq;
	}

	public void start() {
		currentCommand = cmdSeq.getFirst();
		cmdSeq.removeFirst();
		timer.start();
	}
	
	public void doNothing(){
		Robot.hardware.drive.setMotors(0,0);
		
	}

}