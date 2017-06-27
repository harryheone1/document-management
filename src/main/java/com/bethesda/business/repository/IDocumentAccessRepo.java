package com.bethesda.business.repository;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.bethesda.common.error.ErrorInfoEexception;

public interface IDocumentAccessRepo {

	void updateDocmentVersion(String documentName, String version);

	<T> T extractDocumentFromFile(File file, Class<T> resultType) throws ErrorInfoEexception;

	String getDocumentExtension();

	void refreshFile(String fileName, String root, String nodeParentText, List<Map<String, String>> nodes) throws ErrorInfoEexception;
	
}
