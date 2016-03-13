package org.usfirst.frc.team5499.robot.auto;

import java.util.ArrayDeque;
import java.util.HashMap;

public class AutoModeSequences {
	HashMap<String, ArrayDeque<Command>> modes;
	
	public AutoModeSequences(){
		modes.put("do nothing", new ArrayDeque<Command>());
		modes.get("do nothing").addLast(new Command(Command.commandType.CMD_NULL));
		
		modes.put("low bar", new ArrayDeque<Command>());
		modes.get("low bar").addLast(new Command(Command.commandType.CMD_SHIFT, 0, 0, 0, true, true, 0, 0, 0, true, 1));
		modes.get("low bar").addLast(new Command(Command.commandType.CMD_AFLIP, 0, 0, 0, true, true, 0, 0, 0, true, 2));
		modes.get("low bar").addLast(new Command(Command.commandType.CMD_DRIVE, 15, 15, 0, true, true, 0, 0, 0, true, 8));
		modes.get("low bar").addLast(new Command(Command.commandType.CMD_NULL));
		
		modes.put("low bar goal", new ArrayDeque<Command>());
		modes.get("low bar goal").addLast(new Command(Command.commandType.CMD_SHIFT, 0, 0, 0, true, true, 0, 0, 0, true, 1));
		modes.get("low bar goal").addLast(new Command(Command.commandType.CMD_AFLIP, 0, 0, 0, true, true, 0, 0, 0, true, 2));
		modes.get("low bar goal").addLast(new Command(Command.commandType.CMD_DRIVE, 15, 15, 0, true, true, 0, 0, 0, true, 5));
		modes.get("low bar goal").addLast(new Command(Command.commandType.CMD_DRIVE, 15, 15, 30, true, true, 0, 0, 0, true, 3));
		modes.get("low bar goal").addLast(new Command(Command.commandType.CMD_CHANGESHOTSETPOINT, 15, 15, 30, true, true, 5200, 5000, 36, true, 3));
		modes.get("low bar goal").addLast(new Command(Command.commandType.CMD_SHOOT, 15, 15, 30, true, true, 5200, 5000, 36, true, 1));
		modes.get("low bar goal").addLast(new Command(Command.commandType.CMD_NULL));
		
		modes.put("rock wall", new ArrayDeque<Command>());
		modes.get("rock wall").addLast(new Command(Command.commandType.CMD_SHIFT, 0, 0, 0, false, false, 0, 0, 0, false, 1));
		modes.get("rock wall").addLast(new Command(Command.commandType.CMD_DRIVE, 15, 15, 0, false, false, 0, 0, 0, false, 9));
		modes.get("rock wall").addLast(new Command(Command.commandType.CMD_NULL));
		
		modes.put("spy shot", new ArrayDeque<Command>());
		modes.get("spy shot").addLast(new Command(Command.commandType.CMD_SHIFT, 0, 0, 0, false, true, 0, 0, 0, true, 1));
		modes.get("spy shot").addLast(new Command(Command.commandType.CMD_AFLIP, 0, 0, 0, false, true, 0, 0, 0, true, 2));
		modes.get("spy shot").addLast(new Command(Command.commandType.CMD_CHANGESHOTSETPOINT, 0, 0, 0, false, true, 5400, 5200, 51, true, 3));
		modes.get("spy shot").addLast(new Command(Command.commandType.CMD_SHOOT, 0, 0, 0, false, true, 5400, 5200, 51, true, 2));
		modes.get("spy shot").addLast(new Command(Command.commandType.CMD_NULL));
		
	
	
	}
}
