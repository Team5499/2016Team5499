package org.usfirst.frc.team5499.lib.util;

public class Trajectory {
	public static class Segment{
		public double pos, velo, acc, dt;
		
		public Segment(){
		}
		public Segment(double pos, double velo, double acc, double dt){
			this.pos = pos;
			this.velo = velo;
			this.acc = acc;
			this.dt = dt;
		}
		
	}
	
	Segment[] segments;
	public int length;
	
	public Trajectory(int length){
		this.length = length;
		segments = new Segment[length];
		for (int i =0; i<length; i++){
			segments[i] = new Segment();
		}
	}
	public Segment getSegment(int index){
		return segments[index];
	}
	public void setSegment(int index, Segment segment){
		segments[index] = segment;
	}
}
