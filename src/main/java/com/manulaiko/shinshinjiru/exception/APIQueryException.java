package com.manulaiko.shinshinjiru.exception;

/**
 * API Query exception.
 * ====================
 *
 * Exception thrown when executing a query to the API.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class APIQueryException extends RuntimeException {
    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public APIQueryException(String message) {
        super(message);
    }
}
