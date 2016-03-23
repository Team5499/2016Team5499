package org.usfirst.frc.team5499.vision;

import edu.wpi.first.wpilibj.CameraServer;

public class Camera {
	int session;
	CameraServer server;
	public Camera(){		
		
	}
	
	/**
	 * Start the acquisition of the image. Call before you use start capturing with the camera
	 */
	public void startAcquire(){
		server = CameraServer.getInstance();
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam1");
	}
	/**
	 * Start the capture of images. Call periodically to update your image.
	 * Overlays two lines, one vertical, one horizontal
	 */
	public void startCapture(){
	}
	/**
	 * Stop the acquisition of the image. Call when done with the camera.
	 */
	public void stopAcquire(){
	}
}