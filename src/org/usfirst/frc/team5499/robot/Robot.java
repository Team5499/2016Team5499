
package org.usfirst.frc.team5499.robot;

import org.usfirst.frc.team5499.lib.util.MultiLooper;
import org.usfirst.frc.team5499.robot.auto.AudoMode;
import org.usfirst.frc.team5499.robot.auto.AudoModeSequences;
import org.usfirst.frc.team5499.robot.commands.CommandManager;
import org.usfirst.frc.team5499.robot.commands.Commands;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;

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
	AutoMode autoMode;
    AudoModeSequences autoModeSequences;
    Iterator sequenceIterator;

	public static Commands cmds;
	
    @Override
	public void robotInit() {
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
		hardware.drive.gyro.gyro.calibrate();
		autohasshot = false;
		autoMode = new AutoMode();
        autoModeSequences = new AudoModeSequences();
        sequenceIterator = autoModeSequences.entrySet().iterator();
        this.cycleNextAutoMode();

		hardware.shooter.lower();
	}
    
    @Override
    public void autonomousInit() {
    	state = StateEnum.AUTO;
        autoMode.setHardware(hardware);

    	controlLooper.addLoopable(autoMode);
    	controlLooper.start();
		//hardware.drive.setTrajectory(trajPair);
		//hardware.shooter.lower();
		hardware.drive.setInverted(false);
		hardware.encLeft.reset();
		hardware.encRight.reset();
		hardware.drive.gyro.gyro.calibrate();
		hardware.drive.gyro.gyro.reset();
    }

    @Override
    public void autonomousPeriodic() {
    	cmdManager.update(cmds);
    	System.out.println(Robot.hardware.drive.gyro.gyro.getAngle());
    }
    @Override
    public void teleopInit(){
    	state = StateEnum.TELEOP;
    	controlLooper.removeLoopable(autoMode);
    	controlLooper.stop();
    	controlLooper.start();
    	hardware.shooter.stopWheels();
    	hardware.shooter.lower();

    }
    @Override
    public void teleopPeriodic() {
    //	System.out.println(hardware.shooter.getTopWheelSpeed());
    	Commands cmds = hardware.operatorStation.getCommands();
    	System.out.println(Robot.hardware.drive.gyro.gyro.getAngle());
    	cmdManager.update(cmds);
    }
    @Override
    public void testPeriodic() {
    
    }
    @Override
    public void disabledInit(){
    	controlLooper.stop();
    }
    @Override
    public void disabledPeriodic(){
        if(hardware.operatorStation.getButton(james pick a button))
        {
            Timer.delay(0.5);
            this.cycleNextAutoMode();
        }
    }

    public void cycleNextAutoMode() {
        if(!sequenceIterator.hasNext()) {
            sequenceIterator = autoModeSequences.entrySet().iterator();
        }
        Entry sequence = (Entry)sequenceIterator.next();
        System.out.println(sequence.getKey());
        autoMode.setCommandSequence(sequence.getValue());
    }
    
    public static StateEnum getState(){
    	return state;
    }
    
}
