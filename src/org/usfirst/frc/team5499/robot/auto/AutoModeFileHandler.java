package org.usfirst.frc.team5499.robot.auto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AutoModeFileHandler {
	public final String fileName = "StraightAhead.txt";
	
	public String readAutoModeFile(){
		StringBuffer strbf = new StringBuffer();
		FileReader fr = null;
		BufferedReader br = null;
		String line = "";
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			System.out.println("file loaded");
		} catch (FileNotFoundException e) {
			System.out.println("fileload failed");
			e.printStackTrace();
		}
		try {
			if(br!= null){
				while((line = br.readLine()) != null) {
					strbf.append(line).append("\n");
				}
			}else{
				System.out.println("br is null");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return strbf.toString();
	}
}
