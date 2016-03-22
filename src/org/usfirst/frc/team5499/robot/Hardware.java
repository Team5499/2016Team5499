package org.usfirst.frc.team5499.robot;

import org.usfirst.frc.team5499.robot.controllers.GenericController;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class Hardware {
	private GenericController m_Controller;
	
	private USBCamera m_Camera;
	
	public Hardware() {
		m_Camera = new USBCamera();
		CameraServer.getInstance().startAutomaticCapture(m_Camera);
	}
	
	public void SetController(GenericController controller) {
		m_Controller = controller;
	}
	
	public void RunPeriodic() {
		m_Controller.RunPeriodic(this);
	}
}
