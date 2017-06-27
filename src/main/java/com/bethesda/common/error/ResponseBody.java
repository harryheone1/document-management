package com.bethesda.common.error;

public class ResponseBody {

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	private String code;

    private String message;

    private Object result;

    public ResponseBody(ErrorInfo errorInfo) {
        this.code = errorInfo.getCode();
        this.message = errorInfo.getMessage();
    }

    public ResponseBody(Object result) {
        this.code = ErrorInfoEnum.SUCCESS.getCode();
        this.message = ErrorInfoEnum.SUCCESS.getMessage();
        this.result = result;
    }
}
