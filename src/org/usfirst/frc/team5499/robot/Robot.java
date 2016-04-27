
package org.usfirst.frc.team5499.robot;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Map.Entry;

import org.usfirst.frc.team5499.lib.util.Looper;
import org.usfirst.frc.team5499.lib.util.MultiLooper;
import org.usfirst.frc.team5499.robot.auto.AutoMode;
import org.usfirst.frc.team5499.robot.auto.AutoModeSequences;
import org.usfirst.frc.team5499.robot.auto.Command;
import org.usfirst.frc.team5499.robot.commands.CommandManager;
import org.usfirst.frc.team5499.robot.commands.Commands;
import org.usfirst.frc.team5499.robot.subsystems.OI.StickEnum;

import com.team1538.lib.CowAlphaNum;
import com.team1538.lib.CowGyro;
import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;


public class Robot extends IterativeRobot {
	public static enum StateEnum{
		AUTO,
		TELEOP,
		DISABLED
	}
	
	MultiLooper controlLooper = new MultiLooper("controllers", 1 / 200.0);
	Looper httpRequestLooper;
	public static Hardware hardware;
	static StateEnum state;
	CommandManager cmdManager; 
	Path autoModePath;
	Trajectory.Pair trajPair;
	String fileString;
	boolean autohasshot;
	AutoMode autoMode;
	AndroidHandler androidHandler;
    AutoModeSequences autoModeSequences;
    Iterator<Entry<String, ArrayDeque<Command>>> sequenceIterator;
    
    public static String theta;
	public static Commands cmds;
		
    @Override
	public void robotInit() {
    	androidHandler = new AndroidHandler();
    	androidHandler.init();
    	httpRequestLooper = new Looper("httprequest", androidHandler, 1 / 10.0);
    	Timer.delay(1);
    	System.out.println("robotInit");
    	hardware = new Hardware();
		controlLooper.addLoopable(hardware.shooter);
		controlLooper.addLoopable(hardware.drive);
		controlLooper.addLoopable(hardware.intake);
		controlLooper.addLoopable(hardware.aflip);
		cmdManager = new CommandManager();
//		fileString = new AutoModeFileHandler().readAutoModeFile();
//		autoModePath = (new TextFileDeserializer()).deserialize(fileString);
//		trajPair = autoModePath.getPair();
//		System.out.println(trajPair.toString());
		autohasshot = false;
		autoMode = new AutoMode();
        autoModeSequences = new AutoModeSequences();
        sequenceIterator = autoModeSequences.modes.entrySet().iterator();
        
        this.cycleNextAutoMode();
       // autoMode.currentCommand 

		hardware.shooter.lower();
		
		//hardware.camera.startAcquire();
		
		CowAlphaNum.SetBanner("5499");
		CowAlphaNum.DisplayBanner();
	}
    
    @Override
    public void autonomousInit() {
    	state = StateEnum.AUTO;
    	autoMode.start();
    	controlLooper.addLoopable(autoMode);
    	controlLooper.addLoopable(androidHandler);
    	controlLooper.start();
    	httpRequestLooper.stop();
    	httpRequestLooper.start();
		//hardware.drive.setTrajectory(trajPair);
		//hardware.shooter.lower();
		hardware.drive.setInverted(false);
		hardware.encLeft.reset();
		hardware.encRight.reset();
		CowGyro.FinalizeCalibration();
    }

    @Override
    public void autonomousPeriodic() {
//    	cmdManager.update(cmds);
//    	System.out.println(Robot.hardware.drive.gyro.gyro.getAngle());
    }
    @Override
    public void teleopInit(){
    	state = StateEnum.TELEOP;
    	controlLooper.removeLoopable(autoMode);
    	controlLooper.stop();
    	controlLooper.start();
    	httpRequestLooper.stop();
    	httpRequestLooper.start();
    	hardware.shooter.stopWheels();
    	hardware.shooter.lower();
    	CowGyro.FinalizeCalibration();
        hardware.driveLeft1.enable();
        hardware.driveLeft2.enable();
        hardware.driveRight1.enable();
        hardware.driveRight2.enable();

    }
    @Override
    public void teleopPeriodic() {
    //	System.out.println(hardware.shooter.getTopWheelSpeed());
    	Commands cmds = hardware.operatorStation.getCommands();
    	//System.out.println(hardware.drive.encLeft.getDistance() + " left");
    	//System.out.println(hardware.drive.encRight.getDistance()+ " right");
    	System.out.println(hardware.shooterArmPot.getInput());
    	cmdManager.update(cmds);
    }
    @Override
    public void testPeriodic() {
    
    }
    @Override
    public void disabledInit(){
    	controlLooper.stop();
    	httpRequestLooper.stop();
    	httpRequestLooper.start();
    	CowGyro.BeginCalibration();
//        Reference.shooterArmPotZero =  -1 * hardware.shooterArmPot.getInput();
//        hardware.shooterArmPot = new Pot(Reference.shooterArmPotAIPort);
    }
    @Override
    public void disabledPeriodic(){
        if(hardware.operatorStation.getButton(StickEnum.OPERATOR, 7))
        {
        	System.out.println("switch button pushed");
            Timer.delay(0.5);
            this.cycleNextAutoMode();
        }
      //  System.out.println(Robot.hardware.shooterArmPot.get());


    }

    public void cycleNextAutoMode() {
        if(!sequenceIterator.hasNext()) {
            sequenceIterator = autoModeSequences.modes.entrySet().iterator();
        }
        Entry<String, ArrayDeque<Command>> sequence = (Entry<String, ArrayDeque<Command>>)sequenceIterator.next();
        System.out.println(sequence.getKey());
        autoMode.setCommandSequence((ArrayDeque<Command>)sequence.getValue());
    }
    
    public static StateEnum getState(){
    	return state;
    }
    
}
