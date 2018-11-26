package com.thtf.app.web.rest.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalInvokeUtil {
	private final static Logger log = LoggerFactory.getLogger(LocalInvokeUtil.class);
	
	public static String executeCommand( String command){
		Process process;
		String ret = "";
		try {
			Runtime runtime = Runtime.getRuntime();
			process = runtime.exec(command);	 
			ret += StreamUtil.processInputStream(process.getErrorStream());
		    ret += StreamUtil.processInputStream(process.getInputStream());
		} catch (IOException e) {
			log.error("iOE exception",e);
		}
		
		return ret;
	}
}
