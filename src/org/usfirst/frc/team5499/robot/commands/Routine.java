package org.usfirst.frc.team5499.robot.commands;

import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.subsystems.Drive;
import org.usfirst.frc.team5499.robot.subsystems.Intake;
import org.usfirst.frc.team5499.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

public abstract class Routine {
	Drive drive = Robot.hardware.drive;
	Intake intake = Robot.hardware.intake;
	Shooter shooter = Robot.hardware.shooter;
	PowerDistributionPanel pdp = Robot.hardware.pdp;
	
	public abstract RobotState update(Commands commands, RobotState state);
	
}
