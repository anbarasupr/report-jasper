package com.spring.jasperreports.exception;

public abstract class JasperException extends RuntimeException {
	public JasperException(String s) {
		super(s);
	}

	public JasperException(String s, Throwable throwable) {
		super(s, throwable);
	}
}
