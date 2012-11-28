package br.com.m2msolutions.client;

import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.i18n.client.DateTimeFormat;

@SuppressWarnings("deprecation")
public class SimpleGwtLogger extends JavaScriptObject{
	
	protected SimpleGwtLogger() {
	}

	public static native boolean isAvailable()/*-{
		if ($wnd.console == null || $wnd.console == "undefined") {
			return false;
		} else {
			return true;
		}
	}-*/;

	public static void log(String message) {
		if (isAvailable()) {
			DateTimeFormat dt = DateTimeFormat.getLongTimeFormat();
			log_("[ " + dt.format(new Date()) + "] " + message);
		}
	}

	public static void debug(String message) {
		if (isAvailable()) {
			DateTimeFormat dt = DateTimeFormat.getLongTimeFormat();
			debug_("[ " + dt.format(new Date()) + "] " + message);
		}
	}

	public static void info(String message) {
		if (isAvailable()) {
			DateTimeFormat dt = DateTimeFormat.getLongTimeFormat();
			info_("[ " + dt.format(new Date()) + "] " + message);
		}
	}

	public static void warm(String message) {
		if (isAvailable()) {
			DateTimeFormat dt = DateTimeFormat.getLongTimeFormat();
			warm_("[ " + dt.format(new Date()) + "] " + message);
		}
	}

	public static void error(String message, Throwable caught) {
		error("[" + message + "]" + caught.getMessage());
	}


	public static void error(String message) {
		if (isAvailable()) {
			DateTimeFormat dt = DateTimeFormat.getLongTimeFormat();
			error_("[ " + dt.format(new Date()) + "] " + message);
		}
	}

	private static  native void log_(String message)/*-{
		$wnd.console.log(message);
	}-*/;

	private static  native void debug_(String message)/*-{
		$wnd.console.debug(message);
	}-*/;

	private static  native void info_(String message)/*-{
		$wnd.console.info(message);
	}-*/;

	private static  native void warm_(String message)/*-{
		$wnd.console.warm(message);
	}-*/;

	private static native void error_(String message)/*-{
												$wnd.console.error(message);
												}-*/;
}
