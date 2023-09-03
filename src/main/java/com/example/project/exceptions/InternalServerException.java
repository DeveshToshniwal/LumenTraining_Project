package com.example.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends DatabaseException {

    /**
     * DuplicateData Exception
     * @param _msg
     */
    public InternalServerException(String _msg) {
        super(_msg);
    }

    /**
     * DuplicateData Exception
     * @param _msg
     * @param _e
     */
    public InternalServerException(String _msg, Throwable _e) {
        super(_msg, HttpStatus.BAD_REQUEST, _e);
    }

    /**
     * DuplicateData Exception
     * @param _e
     */
    public InternalServerException(Throwable _e) {
        super("Duplicate Data Exception!", HttpStatus.BAD_REQUEST, _e);
    }
}