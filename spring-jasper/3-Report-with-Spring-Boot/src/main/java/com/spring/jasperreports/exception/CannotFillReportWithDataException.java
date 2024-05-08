package com.spring.jasperreports.exception;

public class CannotFillReportWithDataException extends JasperException {
	public CannotFillReportWithDataException(String s) {
		super(s);
	}

	public CannotFillReportWithDataException(String s, Throwable throwable) {
		super(s, throwable);
	}
}
