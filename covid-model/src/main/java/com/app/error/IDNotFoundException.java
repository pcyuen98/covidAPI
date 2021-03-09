package com.app.error;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IDNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9137979495865049696L;

	public IDNotFoundException(Long id) {
		super("Book id not found : " + id);
		log.error("BookNotFoundException  Book id not found :" + id);
	}

}
