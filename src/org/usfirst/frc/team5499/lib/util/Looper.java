package org.usfirst.frc.team5499.lib.util;

import java.util.Timer;
import java.util.TimerTask;

public class Looper {
		private double period = 1.0 / 100.0;
	    protected Loopable loopable;
	    private Timer looperUpdater;
	    protected String name;

	    public Looper(String name, Loopable loopable, double period) {
	        this.period = period;
	        this.loopable = loopable;
	        this.name = name;
	    }

	    private class UpdaterTask extends TimerTask {

	        private Looper looper;

	        public UpdaterTask(Looper looper) {
	            if (looper == null) {
	                throw new NullPointerException("Looper was null");
	            }
	            this.looper = looper;
	        }

	        public void run() {
	            looper.update();
	        }
	    }

	    public void start() {
	        if (looperUpdater == null) {
	            looperUpdater = new Timer("Looper - " + this.name);
	            looperUpdater.schedule(new UpdaterTask(this), 0L, (long) (this.period * 1000));
	        }
	    }

	    public void stop() {
	        if (looperUpdater != null) {
	            looperUpdater.cancel();
	            looperUpdater = null;
	        }
	    }

	    private void update() {
	        loopable.update();
	    }

}
