package com.openthinks.tasks.web.utils.applet.support;

public class AppletLogger {

	public static void log(Exception e) {
		e.printStackTrace();
	}

	public static void log(String message) {
		System.out.println(message);
	}

}
