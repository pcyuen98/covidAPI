package com.app.error;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ControllerException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControllerException(String url, String message) {
		super(url + com.app.util.CovidDefaultConstant.COVID_APP + " controller exception--> " + message);
		log.error(url + " ControllerExceptiond :" + message);
	}
}
