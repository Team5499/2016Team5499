package org.usfirst.frc.team5499.robot.auto;

import java.util.ArrayDeque;

import org.usfirst.frc.team5499.lib.util.Loopable;

public class AutoMode implements Loopable{
	
	ArrayDeque<Command> cmdSeq;
	Command currentCommand;
	
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
					// run intake down
				} else {
					// run intake up
				}
				break;
			case Command.commandType.CMD_AFLIP:
				if(currentCommand.aflipstate)
				{
					// run aflip down
				} else {
					// run flip up
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
				break;s
		if(result) { // OR we run out of time
			// get next command
		}
	}
	
	public void setCommandSequence(ArrayDeque<Command> cmdSeq){
		this.cmdSeq = cmdSeq;
		currentCommand = cmdSeq.getFirst();
		cmdSeq.removeFirst();
	}

}
