package org.usfirst.frc.team5499.robot;

import org.json.JSONObject;
import org.spectrum3847.RIOdroid.RIOadb;
import org.spectrum3847.RIOdroid.RIOdroid;
import org.usfirst.frc.team5499.lib.util.Loopable;

public class AndroidHandler implements Loopable {

	public double theta;
	public double dist;
	
	public AndroidHandler(){
		
	}
	
	public void init(){
		RIOdroid.init();
    	RIOadb.init();
    	RIOdroid.executeCommand("adb start-server");
    	RIOdroid.executeCommand("adb forward tcp:80 tcp:8080");
	}
	
	@Override
	public void update() {
		
		//Http request from localhost:80
		String jsonString = "{'theta':0,'dist':0}"; //replace with http request
		//parse json
		JSONObject json = new JSONObject(jsonString);
		theta = json.getDouble("theta");
		dist = json.getDouble("dist");
		
	}

}
