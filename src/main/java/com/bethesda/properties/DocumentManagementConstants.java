package com.bethesda.properties;

public final class DocumentManagementConstants {

	public static final String SERVER_DICTIONNARY_PATH = "/Users/hejunz/src/Java/eworkspace/file-management/src/main/resource";
	public static final String SERVER_DICTIONNARY_NAME = "server.xml";
	public static final String CLIENT_DICTIONNARY_PATH = "/Users/hejunz/src/Java/eworkspace/file-management/src/main/resource";
	public static final String CLIENT_DICTIONNARY_NAME = "client.xml";
	public static final String FILE_BASE_PATH ="/Users/hejunz/src/Java/eworkspace/file-management/src/main/resource/file";

	public static String getClientDictionaryName() {
		return DocumentManagementConstants.CLIENT_DICTIONNARY_PATH + "/" + DocumentManagementConstants.CLIENT_DICTIONNARY_NAME;
	}
	
	public static String getServerDictionaryName() {
		return DocumentManagementConstants.SERVER_DICTIONNARY_PATH + "/" + DocumentManagementConstants.SERVER_DICTIONNARY_NAME;
	}
}
