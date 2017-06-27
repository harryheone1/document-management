package com.bethesda.common.properties.service;

import java.util.List;

public interface IPropertiesService {

	String getClientDictionaryFileFullName();

	String getServerDictionaryFileFullName();
	
	String getDcoumentFileBasePath();
	
	String getClientDictionnaryFileRootElement();
	
	String getServerDictionnaryFileRootElement();

	String getClientDictionnaryFileParentElement();
	
	String getServerDictionnaryFileParentElement();

	List<String> getClientDictionaryNodeList();

	List<String> getServerDictionaryNodeList();
}
