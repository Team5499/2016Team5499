package org.usfirst.frc.team5499.robot.auto;

import java.util.ArrayDeque;

import org.usfirst.frc.team5499.lib.util.Loopable;

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
				// PID the distance
				base_speed = result of distance PID
				// PID the heading
				offset_speed = results of heading PID
				left = base + offset
				right = base - offset
				if(close enoguht o gaols) {
					result = true;
				}
				break;
			case Command.commandType.CMD_CHANGESHOTSETPOINT:
				break;
			case Command.commandType.CMD_SHOOT:
				break;
		if(result || timer.get() > currentCommand.timeout) {
			if(currentCommand.commandType != Command.commandType.CMD_NULL) {
				if(cmdSeq.size() == 0)
				{
					currentCommand = new Command();
					currentCommand.commandType = CMD_NULL;
				} else {
					urrentCommand = cmdSeq.getFirst();
					cmdSeq.removeFirst();
					timer.start();
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
