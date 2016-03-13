package org.usfirst.frc.team5499.robot.auto;

import org.usfirst.frc.team5499.lib.util.Loopable;
import org.usfirst.frc.team5499.robot.Reference;
import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.commands.Commands;

import edu.wpi.first.wpilibj.Timer;

public class AutoModeBreach implements Loopable{
	
	public boolean readyToShoot;
	public boolean shootFinished;
	public boolean shotPrepped;
	boolean adown;
	int currentsetpointindex;
	double setpointsang[] = {0};//90, 0, -45};
	double setpointspos[] = {15};//0, 11.75, 0};
	boolean posControls[] = {true};
	double shoottime;
	
	public AutoModeBreach(){
		currentsetpointindex = 0;
		shotPrepped = false;
		shoottime = 0;
	}
	
	public Commands getCmds(){
		System.out.println("commands getting");
		Commands cmds = new Commands();
		if(!adown){
			Robot.hardware.aflip.lowerArm();
			adown = true;
			Timer.delay(1);
		}else{
			Robot.hardware.drive.angControl.setSetpoint(setpointsang[currentsetpointindex]);
			Robot.hardware.drive.posControl = posControls[currentsetpointindex];
			cmds.shiftRequest = Commands.ShiftRequest.HIGH;
			//Robot.hardware.drive.low = true;
			if(!Robot.hardware.drive.posControl){
				Robot.hardware.drive.leftPosControl.turnOff(true);
				Robot.hardware.drive.rightPosControl.turnOff(true);
				//Robot.hardware.drive.posControl = false;
			}else{
				Robot.hardware.drive.leftPosControl.turnOff(false);
				Robot.hardware.drive.rightPosControl.turnOff(false);
				//Robot.hardware.drive.posControl = true;
				Robot.hardware.drive.leftPosControl.setSetpoint(setpointspos[currentsetpointindex]);
				Robot.hardware.drive.rightPosControl.setSetpoint(setpointspos[currentsetpointindex]);
			}
			if(Robot.hardware.drive.encLeft.getDistance() > 15){
				Robot.hardware.drive.controlOff();
				Robot.hardware.drive.setMotors(0,0);
//				if(Math.abs(Timer.getFPGATimestamp() - shoottime) > 1 && shotPrepped){
//					cmds.shootRequest = Commands.Shoot.ON;
//				}else{
//					cmds.shotPrepRequest = Commands.ShotRequest.AUTO;
//					shoottime = Timer.getFPGATimestamp();
//					shotPrepped = true;
//				}
				
			}
			//Robot.hardware.drive.update();
			if(Robot.hardware.drive.leftPosControl.isFinished() && Robot.hardware.drive.rightPosControl.isFinished()){			
				System.out.println("");
				currentsetpointindex++;
				Robot.hardware.drive.encLeft.reset();
				Robot.hardware.drive.encRight.reset();
				//Robot.hardware.drive.gyro.gyro.reset();
				System.out.println(currentsetpointindex);
				if(currentsetpointindex > 0){

					//readyToShoot = true;
					Robot.hardware.drive.controlOff();
					Robot.hardware.drive.setMotors(0, 0);
				}
			}
		}
		return cmds;
	}

	@Override
	public void update() {
		Commands cmds = getCmds();
		Robot.cmds = cmds;
	}
	
	
	
}
