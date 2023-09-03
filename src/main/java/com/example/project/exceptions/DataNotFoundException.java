package com.example.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataNotFoundException extends DatabaseException {

    /**
     * DataNotFound Exception
     * @param _msg
     */
    public DataNotFoundException(String _msg) {
        super(_msg, HttpStatus.NOT_FOUND, null);
    }

    /**
     * DataNotFound Exception
     * @param _msg
     * @param _e
     */
    public DataNotFoundException(String _msg, Throwable _e) {
        super(_msg, HttpStatus.NOT_FOUND, _e);
    }

    /**
     * DataNotFound Exception
     * @param _e
     */
    public DataNotFoundException(Throwable _e) {
        super("Data Not Found!", HttpStatus.NOT_FOUND, _e);
    }
}
