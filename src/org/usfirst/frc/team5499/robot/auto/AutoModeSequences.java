package org.usfirst.frc.team5499.robot.auto;

import java.util.ArrayDeque;
import java.util.HashMap;

import org.usfirst.frc.team5499.robot.auto.Command.IntakeRollerState;
import org.usfirst.frc.team5499.robot.commands.Commands;

public class AutoModeSequences {
	public HashMap<String, ArrayDeque<Command>> modes;
	
	public AutoModeSequences(){
		modes = new HashMap<>();
		modes.put("do nothing", new ArrayDeque<Command>());
		modes.get("do nothing").addLast(new Command(Command.commandType.CMD_NULL));
		
		modes.put("1 ball", new ArrayDeque<Command>());
		modes.get("1 ball").addLast(new Command(Command.commandType.CMD_SHIFT, 0, 0, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 1, IntakeRollerState.OFF));
		modes.get("1 ball").addLast(new Command(Command.commandType.CMD_AFLIP, 0, 0, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, .1, IntakeRollerState.OFF));
		modes.get("1 ball").addLast(new Command(Command.commandType.CMD_INTAKE, 0, 0, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 1, IntakeRollerState.OFF));
		modes.get("1 ball").addLast(new Command(Command.commandType.CMD_DRIVE, -14.15, -13.15, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 8, IntakeRollerState.OFF));
		modes.get("1 ball").addLast(new Command(Command.commandType.CMD_WAIT, 0, 0, 0, true, true, Commands.ShotRequest.OFF,0, 0, Commands.ShiftRequest.HIGH, .5, IntakeRollerState.OFF));
		modes.get("1 ball").addLast(new Command(Command.commandType.CMD_TURN, -13.65, -13.65, 52, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 3, IntakeRollerState.OFF));
		modes.get("1 ball").addLast(new Command(Command.commandType.CMD_WAIT, 0, 0, 0, true, true, Commands.ShotRequest.OFF,0, 0, Commands.ShiftRequest.HIGH, .5, IntakeRollerState.OFF));
		modes.get("1 ball").addLast(new Command(Command.commandType.CMD_DRIVE, -7, -7, 52, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 8, IntakeRollerState.OFF));
		modes.get("1 ball").addLast(new Command(Command.commandType.CMD_CHANGE_SHOT, -8, -8, 0, true, true, Commands.ShotRequest.CLEAT, 5200, 5000, Commands.ShiftRequest.HIGH, 2, IntakeRollerState.OFF));
		modes.get("1 ball").addLast(new Command(Command.commandType.CMD_SHOOT, -8, -8, 0, true, true, Commands.ShotRequest.CLEAT, 5200, 5000, Commands.ShiftRequest.HIGH, 1, IntakeRollerState.OFF));
		
		modes.put("2 ball", new ArrayDeque<Command>());
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_SHIFT, 0, 0, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 1, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_AFLIP, 0, 0, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, .1,IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_INTAKE, 0, 0, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, .5, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_DRIVE, -14.65, -14.65, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 8, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_WAIT, 0, 0, 0, true, true, Commands.ShotRequest.OFF,0, 0, Commands.ShiftRequest.HIGH, .5, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_TURN, -14.65, -14.65, 52, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 3, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_WAIT, 0, 0, 0, true, true, Commands.ShotRequest.OFF,0, 0, Commands.ShiftRequest.HIGH, .5, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_DRIVE, -7, -7, 52, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 4, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_CHANGE_SHOT, -8, -8, 0, true, true, Commands.ShotRequest.CLEAT, 5200, 5000, Commands.ShiftRequest.HIGH, 2, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_SHOOT, -7.5, -7.5, 0, true, true, Commands.ShotRequest.CLEAT, 5200, 5000, Commands.ShiftRequest.HIGH, 1, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_SHOOTERDOWN, -8, -8, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 1, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_DRIVE, 8, 8, 52, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 8, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_TURN, 7,7, 0, true, true, Commands.ShotRequest.OFF, 0, 0,Commands.ShiftRequest.HIGH, 3, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_DRIVE, 13, 13, 0, true, true, Commands.ShotRequest.OFF, 0,0, Commands.ShiftRequest.HIGH, 8, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_TURN, 13, 13, -15, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 2, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_INTAKEROLLER, 1, 1, -15, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, .5, IntakeRollerState.IN));		
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_DRIVE, 2, 2, -15, true, true, Commands.ShotRequest.OFF, 0,0, Commands.ShiftRequest.HIGH, 2, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_INTAKE, 1, 1, -15, false, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, .5, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_INTAKE, 1, 1, -15, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, .5, IntakeRollerState.OFF));
//		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_INTAKEROLLER, 1, 1, -15, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, .5, IntakeRollerState.OUT));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_INTAKEROLLER, 1, 1, -15, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, .5, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_DRIVE, -2, -2, -15, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, .5, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_TURN, -2, -2, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 2, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_DRIVE, -3, -3, 0, true, true, Commands.ShotRequest.OFF, 0,0, Commands.ShiftRequest.HIGH, 8, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_DRIVE, -3, -3, 40, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 8, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_CHANGE_SHOT, -8, -8, 0, true, true, Commands.ShotRequest.AUTO, 5200, 5000, Commands.ShiftRequest.HIGH, 2, IntakeRollerState.OFF));
		modes.get("2 ball").addLast(new Command(Command.commandType.CMD_SHOOT, -8, -8, 0, true, true, Commands.ShotRequest.AUTO, 5200, 5000, Commands.ShiftRequest.HIGH, 1, IntakeRollerState.OFF));
		
		
	
		


		modes.put("low bar", new ArrayDeque<Command>());
		modes.get("low bar").addLast(new Command(Command.commandType.CMD_SHIFT, 0, 0, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 1, IntakeRollerState.OFF));
		modes.get("low bar").addLast(new Command(Command.commandType.CMD_AFLIP, 0, 0, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, .1, IntakeRollerState.OFF));
		modes.get("low bar").addLast(new Command(Command.commandType.CMD_INTAKE, 0, 0, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, .5, IntakeRollerState.OFF));
		modes.get("low bar").addLast(new Command(Command.commandType.CMD_DRIVE, -15, -15, 0, true, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 8, IntakeRollerState.OFF));
		

		modes.put("rock wall", new ArrayDeque<Command>());
		modes.get("rock wall").addLast(new Command(Command.commandType.CMD_SHIFT, 0, 0, 0, false, false, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.LOW, 1, IntakeRollerState.OFF));
		modes.get("rock wall").addLast(new Command(Command.commandType.CMD_DRIVE, 15, 15, 0, false, false, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.LOW, 9, IntakeRollerState.OFF));
		
		modes.put("spy shot", new ArrayDeque<Command>());
		modes.get("spy shot").addLast(new Command(Command.commandType.CMD_SHIFT, 0, 0, 0, false, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 1, IntakeRollerState.OFF));
		modes.get("spy shot").addLast(new Command(Command.commandType.CMD_AFLIP, 0, 0, 0, false, true, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 2, IntakeRollerState.OFF));
		modes.get("spy shot").addLast(new Command(Command.commandType.CMD_CHANGE_SHOT, 0, 0, 0, false, true, Commands.ShotRequest.SPY, 5400, 5200, Commands.ShiftRequest.HIGH, 3, IntakeRollerState.OFF));
		modes.get("spy shot").addLast(new Command(Command.commandType.CMD_SHOOT, 0, 0, 0, false, true, Commands.ShotRequest.SPY, 5400, 5200, Commands.ShiftRequest.HIGH, 2, IntakeRollerState.OFF));	
	
		modes.put("test turn in place", new ArrayDeque<Command>());
		modes.get("test turn in place").addLast(new Command(Command.commandType.CMD_TURN, 0, 0, 30, false, false, Commands.ShotRequest.OFF, 0, 0, Commands.ShiftRequest.HIGH, 5, IntakeRollerState.OFF));
		//modes.get("test turn in place").addLast(new Command(Command.commandType.CMD_CHANGE_SHOT, 0, 0, 30, true, true, Commands.ShotRequest.AUTO, 5200, 5000, Commands.ShiftRequest.HIGH, 3));
		//modes.get("test turn in place").addLast(new Command(Command.commandType.CMD_SHOOT, 0, 0, 30, true, true, Commands.ShotRequest.AUTO, 5200, 5000, Commands.ShiftRequest.HIGH, 1));
	
	}
}
