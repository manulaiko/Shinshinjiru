package com.manulaiko.shinshinjiru.exception;

/**
 * Invalid OAuth response exception.
 * =================================
 *
 * Exception thrown when the OAuth response is not valid.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class InvalidOAuthResponseException extends RuntimeException {
    /**
     * Constructor.
     */
    public InvalidOAuthResponseException() {
        super("Invalid OAuth response!");
    }
}
