
package org.usfirst.frc.team5499.robot;

import org.usfirst.frc.team5499.lib.util.MultiLooper;
import org.usfirst.frc.team5499.robot.auto.AutoModeFileHandler;
import org.usfirst.frc.team5499.robot.auto.AutoModeSingleBall;
import org.usfirst.frc.team5499.robot.commands.CommandManager;
import org.usfirst.frc.team5499.robot.commands.Commands;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.io.TextFileDeserializer;

import edu.wpi.first.wpilibj.IterativeRobot;


public class Robot extends IterativeRobot {
	public static enum StateEnum{
		AUTO,
		TELEOP,
		DISABLED
	}
	
	MultiLooper controlLooper = new MultiLooper("controllers", 1 / 200.0);
	public static Hardware hardware;
	static StateEnum state;
	CommandManager cmdManager; 
	Path autoModePath;
	Trajectory.Pair trajPair;
	String fileString;
	boolean autohasshot;
	AutoModeSingleBall autoMode;
	
    @Override
	public void robotInit() {
    	System.out.println("robotInit");
    	hardware = new Hardware();
		controlLooper.addLoopable(hardware.shooter);
		controlLooper.addLoopable(hardware.drive);
		controlLooper.addLoopable(hardware.intake);
		cmdManager = new CommandManager();
		fileString = new AutoModeFileHandler().readAutoModeFile();
		autoModePath = (new TextFileDeserializer()).deserialize(fileString);
		trajPair = autoModePath.getPair();
		System.out.println(trajPair.toString());
		hardware.drive.gyro.gyro.calibrate();
		autohasshot = false;
		autoMode = new AutoModeSingleBall();
	}
    
    @Override
    public void autonomousInit() {
//    	state = StateEnum.AUTO;
//    	controlLooper.start();
    }

    @Override
    public void autonomousPeriodic() {
//    	Commands cmds = autoMode.getCmds();
//    	cmdManager.update(cmds);
    }
    @Override
    public void teleopInit(){
    	state = StateEnum.TELEOP;
    	controlLooper.start();
    	hardware.shooter.stopWheels();
    	hardware.shooter.lower();

    }
    @Override
    public void teleopPeriodic() {
    //	System.out.println(hardware.shooter.getTopWheelSpeed());
    	Commands cmds = hardware.operatorStation.getCommands();
    	//System.out.println(cmds.shiftRequest);
    	cmdManager.update(cmds);
    }
    @Override
    public void testPeriodic() {
    
    }
    @Override
    public void disabledInit(){
    	controlLooper.stop();
    }
    
    public static StateEnum getState(){
    	return state;
    }
    
}
