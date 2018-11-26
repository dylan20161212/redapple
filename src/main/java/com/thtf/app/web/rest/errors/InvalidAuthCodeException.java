package com.thtf.app.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class InvalidAuthCodeException extends AbstractThrowableProblem {

    public InvalidAuthCodeException() {
        super(ErrorConstants.INVALID_AUTHCODE_TYPE, "Incorrect authcode", Status.BAD_REQUEST);
    }
}
