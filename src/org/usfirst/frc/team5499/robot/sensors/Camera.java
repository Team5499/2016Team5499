package org.usfirst.frc.team5499.robot.sensors;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.IMAQdxCameraControlMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.Point;

import edu.wpi.first.wpilibj.CameraServer;

public class Camera {
	int session;
	Image frame;
	CameraServer server;
	NIVision.Rect rect;
	public Camera(){		
		server = CameraServer.getInstance();
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam0");
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        session = NIVision.IMAQdxOpenCamera("cam0", IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
	}
	
	/**
	 * Start the acquisition of the image. Call before you use start capturing with the camera
	 */
	public void startAcquire(){
		NIVision.IMAQdxStartAcquisition(session);
		rect = new NIVision.Rect(10, 10, 100, 100);
	}
	/**
	 * Start the capture of images. Call periodically to update your image.
	 * Overlays two lines, one vertical, one horizontal
	 */
	public void startCapture(){
		//Grab image
		NIVision.IMAQdxGrab(session, frame, 1);
		//Overlay lines-Horizontal
		NIVision.imaqDrawLineOnImage(frame, frame, DrawMode.DRAW_VALUE, new Point(0, 50), new Point(100, 50), 0.0f);
		//Overlay lines-Vertical
		NIVision.imaqDrawLineOnImage(frame, frame, DrawMode.DRAW_VALUE, new Point(50, 0), new Point(50, 100), 0.0f);
		//Send the image to the default dashboard
		CameraServer.getInstance().setImage(frame);
		//Send the image to the SmartDashboard - alt method for ^
		//SmartDashboard.putData("Camera", (Sendable) CameraServer.getInstance());//TODO has not been tested
	}
	/**
	 * Stop the acquisition of the image. Call when done with the camera.
	 */
	public void stopAcquire(){
		NIVision.IMAQdxStopAcquisition(session);
	}
}
