package com.athleticspot.training.application.exception;

import com.athleticspot.web.rest.errors.CustomParameterizedException;

/**
 * @author Tomasz Kasprzycki
 */
public class SurveyAlreadyAssignException extends CustomParameterizedException{

    public SurveyAlreadyAssignException(String message, String... params) {
        super(message, params);
    }
}
