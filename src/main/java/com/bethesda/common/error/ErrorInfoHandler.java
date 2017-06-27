package com.bethesda.common.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorInfoHandler {

	@ExceptionHandler(value = ErrorInfoEexception.class)
	public ResponseBody errorHandlerOverJson(HttpServletRequest request,
			ErrorInfoEexception exception) {
        ErrorInfo errorInfo = exception.getErrorInfo();
        ResponseBody result = new ResponseBody(errorInfo);
        return result;
    }
}
