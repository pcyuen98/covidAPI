package com.app.error;

import java.util.Set;

public class UnSupportedFieldPatchException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2410878716095894695L;

	public UnSupportedFieldPatchException(Set<String> keys) {
        super("Field " + keys.toString() + " update is not allow.");
    }

}
