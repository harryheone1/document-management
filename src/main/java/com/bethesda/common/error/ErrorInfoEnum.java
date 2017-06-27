package com.bethesda.common.error;

public enum ErrorInfoEnum implements ErrorInfo {

	SUCCESS("0", "success"),
	UNKOWN_ERROR("2","unknow error happened"),
	DOCUMENT_NOT_EXIST("000001","document doesn't exist in the server, please update dictionary"),
    DOCUMENT_NAME_NULL("000002","document name is mandatory"),
    DOCUMENT_VERSION_NULL("000003","document version is mandatory"),
    DOCUMENT_BAD_FORMAT_OR_PATH_ERROR("000004","There is one document in the service is in bad format or server storage exception");


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