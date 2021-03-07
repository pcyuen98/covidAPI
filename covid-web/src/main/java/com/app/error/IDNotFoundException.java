package com.app.error;

public class IDNotFoundException extends RuntimeException {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(IDNotFoundException.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -9137979495865049696L;

	public IDNotFoundException(Long id) {
		super("Book id not found : " + id);
		log.error("BookNotFoundException  Book id not found :" + id);
	}

}
