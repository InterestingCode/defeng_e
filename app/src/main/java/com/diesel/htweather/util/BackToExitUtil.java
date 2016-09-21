package com.diesel.htweather.util;

import android.os.Handler;
import android.os.HandlerThread;

/**
 * Activity实现再按一次back键退出
 */
public class BackToExitUtil {

	private boolean isExit = false;
	
	private Runnable task = new Runnable() {
		@Override
		public void run() {
			isExit = false;
		}
	};

	public void doExitInOneSecond() {
		isExit = true;
		HandlerThread thread = new HandlerThread("doTask");
		thread.start();
		new Handler(thread.getLooper()).postDelayed(task, 1000);
	}

	public boolean isExit() {
		return isExit;
	}

	public void setExit(boolean isExit) {
		this.isExit = isExit;
	}
}
