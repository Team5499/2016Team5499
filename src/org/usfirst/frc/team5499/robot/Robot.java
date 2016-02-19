
package org.usfirst.frc.team5499.robot;

import org.usfirst.frc.team5499.lib.util.MultiLooper;
import org.usfirst.frc.team5499.robot.subsystems.Drive;
import org.usfirst.frc.team5499.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;


public class Robot extends IterativeRobot {
	
	public static enum StateEnum{
		AUTO,
		TELEOP,
		DISABLED
	}
	
	MultiLooper controlLooper = new MultiLooper("controllers", 1 / 200.0);
	public static Hardware hardware  = new Hardware();
	static StateEnum state;
    @Override
	public void robotInit() {
    	System.out.println("robotInit");
		controlLooper.addLoopable(hardware.shooter);
		controlLooper.addLoopable(hardware.drive);
		controlLooper.addLoopable(hardware.intake);
	}
    
    @Override
    public void autonomousInit() {
    }

    @Override
    public void autonomousPeriodic() {
    }
    @Override
    public void teleopInit(){
    	controlLooper.start();
    }
    @Override
    public void teleopPeriodic() {
    //	System.out.println(hardware.shooter.getTopWheelSpeed());
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
