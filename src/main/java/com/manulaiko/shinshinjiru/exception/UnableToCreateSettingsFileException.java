package com.manulaiko.shinshinjiru.exception;

/**
 * Unable to create settings file exception.
 * =========================================
 *
 * Exception thrown when the settings file couldn't be created.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class UnableToCreateSettingsFileException extends RuntimeException {
    /**
     * Constructor.
     */
    public UnableToCreateSettingsFileException() {
        super("The settings file couldn't be created!");
    }
}
