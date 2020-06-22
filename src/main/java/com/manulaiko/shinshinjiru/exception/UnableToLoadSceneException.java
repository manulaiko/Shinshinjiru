package com.manulaiko.shinshinjiru.exception;

/**
 * Unable To Load scene exception.
 * ===============================
 *
 * Exception thrown when the scene couldn't be loaded.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class UnableToLoadSceneException extends RuntimeException {
    /**
     * Constructor.
     *
     * @param e Parent exception
     */
    public UnableToLoadSceneException(Exception e) {
        super(e);
    }
}
