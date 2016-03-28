package org.usfirst.frc.team5499.robot.auto;

import java.util.ArrayDeque;
import java.util.HashMap;

import org.usfirst.frc.team5499.robot.commands.Commands;

public class AutoModeSequences {
	public HashMap<String, ArrayDeque<Command>> modes;
	
	public AutoModeSequences(){
		modes = new HashMap<>();
		modes.put("do nothing", new ArrayDeque<Command>());
		modes.get("do nothing").addLast(new Command(Command.commandType.CMD_NULL));
		
		modes.put("low bar goal", new ArrayDeque<Command>());
		modes.get("low bar goal").addLast(new Command(Command.commandType.CMD_SHIFT, 0, 0, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 1));
		modes.get("low bar goal").addLast(new Command(Command.commandType.CMD_INTAKE, 0, 0, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, .5));
		modes.get("low bar goal").addLast(new Command(Command.commandType.CMD_DRIVE, 14, 14, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, .5));
		modes.get("low bar goal").addLast(new Command(Command.commandType.CMD_TURN, -14.5, -14.5, 60, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 3));
		modes.get("low bar goal").addLast(new Command(Command.commandType.CMD_DRIVESTRAIGHT, 9, 9, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, .5));
		modes.get("low bar goal").addLast(new Command(Command.commandType.CMD_CHANGE_SHOT, -8, -8, 0, true, true, Commands.ShotRequest.AUTO, 5200, 5000, Commands.ShiftRequest.HIGH, 2));
		modes.get("low bar goal").addLast(new Command(Command.commandType.CMD_SHOOT, -8, -8, 0, true, true, Commands.ShotRequest.AUTO, 5200, 5000, Commands.ShiftRequest.HIGH, 1));


		modes.put("low bar", new ArrayDeque<Command>());
		modes.get("low bar").addLast(new Command(Command.commandType.CMD_SHIFT, 0, 0, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 1));
		modes.get("low bar").addLast(new Command(Command.commandType.CMD_AFLIP, 0, 0, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 2));
		modes.get("low bar").addLast(new Command(Command.commandType.CMD_DRIVE, -15, -15, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 8));
				
		modes.put("rock wall", new ArrayDeque<Command>());
		modes.get("rock wall").addLast(new Command(Command.commandType.CMD_SHIFT, 0, 0, 0, false, false, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.LOW, 1));
		modes.get("rock wall").addLast(new Command(Command.commandType.CMD_DRIVE, 15, 15, 0, false, false, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.LOW, 9));
		
		modes.put("spy shot", new ArrayDeque<Command>());
		modes.get("spy shot").addLast(new Command(Command.commandType.CMD_SHIFT, 0, 0, 0, false, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 1));
		modes.get("spy shot").addLast(new Command(Command.commandType.CMD_AFLIP, 0, 0, 0, false, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 2));
		modes.get("spy shot").addLast(new Command(Command.commandType.CMD_CHANGE_SHOT, 0, 0, 0, false, true, Commands.ShotRequest.SPY, 5400, 5200, Commands.ShiftRequest.HIGH, 3));
		modes.get("spy shot").addLast(new Command(Command.commandType.CMD_SHOOT, 0, 0, 0, false, true, Commands.ShotRequest.SPY, 5400, 5200, Commands.ShiftRequest.HIGH, 2));	
	
		modes.put("test turn in place", new ArrayDeque<Command>());
		//modes.get("test turn in place").addLast(new Command(Command.commandType.CMD_TURN, 0, 0, 30, false, false, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 5));
		modes.get("test turn in place").addLast(new Command(Command.commandType.CMD_CHANGE_SHOT, 0, 0, 30, true, true, Commands.ShotRequest.AUTO, 5200, 5000, Commands.ShiftRequest.HIGH, 3));
		modes.get("test turn in place").addLast(new Command(Command.commandType.CMD_SHOOT, 0, 0, 30, true, true, Commands.ShotRequest.AUTO, 5200, 5000, Commands.ShiftRequest.HIGH, 1));
	
	}
}
