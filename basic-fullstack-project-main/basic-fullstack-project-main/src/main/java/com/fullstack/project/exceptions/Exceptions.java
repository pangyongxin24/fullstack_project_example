package com.fullstack.project.exceptions;

/**
 * Define the exceptions that the application can throw.
 */
public class Exceptions {

    /**
     * Nested class that defines the exception thrown when there
     * is a issues accessing the database.
     */
    public static class DbException extends RuntimeException {
	public DbException(Throwable throwable) {
	    super(throwable);
	}

	public DbException(String msg, Throwable throwable) {
	    super(msg, throwable);
	}
    }
}
