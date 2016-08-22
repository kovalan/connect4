package com.games.demo.connect4.exception;

public class Connect4NotFoundException  extends Throwable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8925422122328163153L;

	public Connect4NotFoundException(final String msg) {
        super(msg);
    }

    public Connect4NotFoundException(final String msg, final Throwable ex) {
        super(msg, ex);
    }

    public Connect4NotFoundException(final Throwable ex) {
        super(ex);
    }
}