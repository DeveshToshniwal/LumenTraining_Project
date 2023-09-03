package com.example.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateDataException extends DatabaseException {

    /**
     * DuplicateData Exception
     * @param _msg
     */
    public DuplicateDataException(String _msg) {
        super(_msg);
    }

    /**
     * DuplicateData Exception
     * @param _msg
     * @param _e
     */
    public DuplicateDataException(String _msg, Throwable _e) {
        super(_msg, HttpStatus.BAD_REQUEST, _e);
    }

    /**
     * DuplicateData Exception
     * @param _e
     */
    public DuplicateDataException(Throwable _e) {
        super("Duplicate Data Exception!", HttpStatus.BAD_REQUEST, _e);
    }
}