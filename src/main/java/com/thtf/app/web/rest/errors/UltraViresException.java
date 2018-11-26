package com.thtf.app.web.rest.errors;

public class UltraViresException extends BadRequestAlertException {

	public UltraViresException() {
		super(ErrorConstants.ULTRA_VIRES_TYPE, "ultra vires", "any", "ultravires");
	}

}
