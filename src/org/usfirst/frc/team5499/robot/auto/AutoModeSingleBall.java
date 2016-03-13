package org.usfirst.frc.team5499.robot.auto;

import org.usfirst.frc.team5499.lib.util.Loopable;
import org.usfirst.frc.team5499.robot.Reference;
import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.commands.Commands;

import edu.wpi.first.wpilibj.Timer;

public class AutoModeSingleBall implements Loopable{
	
	public boolean readyToShoot;
	public boolean shootFinished;
	public boolean shotPrepped;
	int currentsetpointindex;
	double setpointsang[] = {30};//90, 0, -45};
	double setpointspos[] = {0};//0, 11.75, 0};
	boolean posControls[] = {true, false, true, false};
	boolean adown;
	
	public AutoModeSingleBall(){
		readyToShoot = false; //false
		shootFinished = false;
		shotPrepped = false;
		adown = false;
		currentsetpointindex = 0;
	}
	
	public Commands getCmds(){
		System.out.println("commands getting");
		Commands cmds = new Commands();
		if(!adown){
//			Robot.hardware.aflip.lowerArm();
//			Timer.delay(.5);
			adown = true;
		}else{
			cmds.shiftRequest = Commands.ShiftRequest.HIGH;
			if(readyToShoot){
				System.out.println("readytoshoot");
				if(shootFinished){
				}else{
					cmds.shotPrepRequest = Commands.ShotRequest.AUTO;

					if(Robot.hardware.shooter.getTopWheelSpeed() > 3500 && Math.abs(Robot.hardware.shooter.armPivotPot.get() - Reference.autoShotAngle) < 5 ){
						shotPrepped = true;
					}
					if(shotPrepped){
						cmds.shotPrepRequest = null;
						cmds.shootRequest = Commands.Shoot.ON;
					}
				}
			}else{
				Robot.hardware.drive.angControl.setSetpoint(setpointsang[currentsetpointindex]);
				Robot.hardware.drive.posControl = posControls[currentsetpointindex];
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
				//Robot.hardware.drive.update();
				if(Robot.hardware.drive.angControl.isFinished() && Robot.hardware.drive.leftPosControl.isFinished() && Robot.hardware.drive.rightPosControl.isFinished()){			
					System.out.println("");
					currentsetpointindex++;
					Robot.hardware.drive.encLeft.reset();
					Robot.hardware.drive.encRight.reset();
					//Robot.hardware.drive.gyro.gyro.reset();
					System.out.println(currentsetpointindex);
					if(currentsetpointindex > 1){

						readyToShoot = true;
						Robot.hardware.drive.controlOff();
						Robot.hardware.drive.setMotors(0, 0);
					}
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
