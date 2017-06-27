package com.bethesda.common.error;

public class ResponseBody {

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
