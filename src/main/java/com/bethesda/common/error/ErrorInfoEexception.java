package com.bethesda.common.error;

public class ErrorInfoEexception extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7106698542857565608L;
	
	private ErrorInfo errorInfo;

    public ErrorInfoEexception (ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }
}
