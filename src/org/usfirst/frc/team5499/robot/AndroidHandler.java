package org.usfirst.frc.team5499.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONObject;
import org.spectrum3847.RIOdroid.RIOadb;
import org.spectrum3847.RIOdroid.RIOdroid;
import org.usfirst.frc.team5499.lib.util.Loopable;

import java.net.URLConnection;

public class AndroidHandler implements Loopable {

	public double theta;
	public double dist;
	
	public AndroidHandler(){
		
	}
	
	public void init(){
		RIOdroid.init();
    	RIOadb.init();
    	System.out.println(RIOdroid.executeCommand("adb start-server"));
    	System.out.println(RIOdroid.executeCommand("adb forward tcp:80 tcp:8080"));
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
	
	public String get(){
		String ret = "";
		URLConnection c;
		BufferedReader in;
		try{
		
		URL url = new URL("localhost:80");
		c = url.openConnection();
		in = new BufferedReader(new InputStreamReader(c.getInputStream()));
		
		ret = in.readLine();
		in.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return ret;
		
	}
}
