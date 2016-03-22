package org.usfirst.frc.team5499.robot;

import org.usfirst.frc.team5499.robot.controllers.*;

public class Robot extends CustomIterativeRobot {
	private Hardware m_Hardware;
	private OperatorController m_OperatorController;
	private AutonomousController m_AutonomousController;
	
	@Override
	public void robotInit() {
		m_Hardware = new Hardware();
		m_OperatorController = new OperatorController();
		m_AutonomousController = new AutonomousController();
	}
	
	@Override
	public void disabledInit() {
	}

	@Override
	public void autonomousInit() {
		m_Hardware.SetController(m_AutonomousController);
	}

	@Override
	public void teleopInit() {
		m_Hardware.SetController(m_OperatorController);
	}

	@Override
	public void disabledPeriodic() {
	}

	@Override
	public void autonomousPeriodic() {
		m_Hardware.RunPeriodic();
	}

	@Override
	public void teleopPeriodic() {
		m_Hardware.RunPeriodic();
	}
}
