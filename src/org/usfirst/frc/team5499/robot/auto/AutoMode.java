package org.usfirst.frc.team5499.robot.auto;

import java.util.ArrayDeque;

import org.usfirst.frc.team5499.lib.util.Loopable;

import org.usfirst.frc.team5499.robot.commands.Commands;

public class AutoMode implements Loopable{
	
	ArrayDeque<Command> cmdSeq;
	Command currentCommand;
	Timer timer;
	Hardware hardware;
	
	@Override
	public void update() {
		boolean result = false;
		switch(currentCommand.type){
			case Command.commandType.CMD_NULL:
				// make robot do nothihng (kill all mtors)
				break;
			case Command.commandType.CMD_WAIT:
				// same here
				break;
			case Command.commandType.CMD_INTAKE:
				if(currentCommand.intakestate)
				{
					hardware.intake.lowerArm();
				} else {
					hardware.intake.raiseArm();
				}
				break;
			case Command.commandType.CMD_AFLIP:
				if(currentCommand.aflipstate)
				{
					hardware.aflip.lowerArm();
				} else {
					hardware.aflip.raiseArm();
				}
				break;
			case Command.commandType.CMD_DRIVE:
				double driveError = currentCommand.encLeftDistance - hardware.drive.leftEnc.getDistance();
				double output = driveError * DRIVE_PID_P; // james fill this in
				hardware.robot.drive.setWheels(output, output);
				if(driveError > -1 && driveError < 1) {
					result = true;
				}
				break;
			case Command.commandType.CMD_TURN:
				double turnError = currentCommand.heading - hardware.drive.gyro.getAngle();
				double output = turnError * TURN_PID_P; // james fill this in
				hardware.robot.drive.setWheels(output, -output);
				if(turnError > -1 && turnError < 1) {
					result = true;
				}
				break;
			case Command.commandType.CMD_CHANGE_SHOT:
				hardware.shooter.setShotSpeeds(currentCommand.shooterShotRequest);
				break;
			case Command.commandType.CMD_SHOOT:
				hardware.shooter.setShotSpeeds(currentCommand.shooterShotRequest);
				hardware.shooter.feed();
				break;
			case Command.commandType.CMD_SHIFT;
				hardware.drive.shift(currentCommand.shiftRequest);
				result = true;
				break;
		}
		if(result || timer.get() > currentCommand.timeout) {
			if(currentCommand.commandType != Command.commandType.CMD_NULL) {
				if(cmdSeq.size() == 0)
				{
					currentCommand = new Command();
					currentCommand.commandType = CMD_NULL;
				} else {
					urrentCommand = cmdSeq.getFirst();
					cmdSeq.removeFirst();
					timer.reset();
				}
			}
		}
	}

	public void setHardware(Hardware hardware) {
		this.hardware = hardware;
	}
	
	public void setCommandSequence(ArrayDeque<Command> cmdSeq){
		this.cmdSeq = cmdSeq;
	}

	public void start() {
		currentCommand = cmdSeq.getFirst();
		cmdSeq.removeFirst();
		timer.start();
	}

}
