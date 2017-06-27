package com.bethesda.common.error;

public enum ErrorInfoEnum implements ErrorInfo {

	SUCCESS("0", "success"),
	UNKOWN_ERROR("2","unknow error happened"),
	PARAMS_NO_COMPLETE("000001","params no complete"),
    CITY_EXIT("000002","city exit");


	private String code;

    private String message;

    ErrorInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}