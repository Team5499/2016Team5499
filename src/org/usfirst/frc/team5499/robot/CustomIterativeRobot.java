package org.usfirst.frc.team5499.robot;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;
import edu.wpi.first.wpilibj.communication.UsageReporting;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary.tInstances;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary.tResourceType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class CustomIterativeRobot extends IterativeRobot {
	static final double ROBOT_PERIOD = 1.0/200.0;
	private ScheduledExecutorService m_scheduler;
	
	public CustomIterativeRobot() {
		m_scheduler = Executors.newSingleThreadScheduledExecutor();
	}

	@Override
	public void startCompetition() {
		// Report that we're an IterativeRobot
		UsageReporting.report(tResourceType.kResourceType_Framework, tInstances.kFramework_Iterative);

		// Call user robotInit
		robotInit();

		// Tell DS we're ready to go
		FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramStarting();
		LiveWindow.setEnabled(false);
		
		// Begin robot loop
		m_scheduler.scheduleAtFixedRate(new RobotTask(this), 0L, (long)(ROBOT_PERIOD * 1000), TimeUnit.MILLISECONDS);
	}
	
	private enum RobotMode {
		NONE,
		DISABLED,
		AUTONOMOUS,
		TEST,
		TELEOP
	};
	
	private class RobotTask implements Runnable {
		private CustomIterativeRobot m_robot;
		private RobotMode m_mode;
		
		public RobotTask(CustomIterativeRobot robot) {
			m_robot = robot;
			m_mode = RobotMode.NONE;
		}
		
		public void run() {
			if (m_robot.isDisabled()) {
				// If we're moving from a different mode to disabled, call user disabledInit
				if (m_mode != RobotMode.DISABLED) {
					LiveWindow.setEnabled(false);
					m_robot.disabledInit();
					m_mode = RobotMode.DISABLED;
				}
				FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramDisabled();
				m_robot.disabledPeriodic();
			} else if (m_robot.isTest()) {
				if (m_mode != RobotMode.TEST) {
					LiveWindow.setEnabled(true);
					m_robot.testInit();
					m_mode = RobotMode.TEST;
				}
				FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramTest();
				m_robot.testPeriodic();
			} else if (m_robot.isAutonomous()) {
				if (m_mode != RobotMode.AUTONOMOUS) {
					LiveWindow.setEnabled(false);
					m_robot.autonomousInit();
					m_mode = RobotMode.AUTONOMOUS;
				}
				FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramAutonomous();
				m_robot.autonomousPeriodic();
			} else {
				if (m_mode != RobotMode.TELEOP) {
					LiveWindow.setEnabled(false);
					m_robot.teleopInit();
					m_mode = RobotMode.TELEOP;
				}
				FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationObserveUserProgramTeleop();
				m_robot.teleopPeriodic();
			}
		}
	}
}
