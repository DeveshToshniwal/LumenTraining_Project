package com.example.project.exceptions;

import org.springframework.http.HttpStatus;

public class DatabaseException extends AbstractServiceException {

    /**
     * Database Exception - Bad Request
     * @param _msg
     */
    public DatabaseException(String _msg) {
        super(_msg);
    }

    /**
     * Database Exception - Bad Request
     * @param _e
     */
    public DatabaseException(Throwable _e) {
        super(_e);
    }

    /**
     * Database Exception - Bad Request
     * @param _msg
     * @param _e
     */
    public DatabaseException(String _msg, Throwable _e) {
        super(_msg, _e);
    }

    /**
     * Database Exception
     * @param _msg
     * @param badRequest
     * @param _e
     */
    public DatabaseException(String _msg, HttpStatus badRequest, Throwable _e) {
        super(_msg, badRequest, _e);
    }
}
