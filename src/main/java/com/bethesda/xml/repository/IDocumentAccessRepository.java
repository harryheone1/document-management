package com.bethesda.xml.repository;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface IDocumentAccessRepository {

	void updateDocmentVersion(String documentName, String version);

	<T> T extractDocumentFromFile(File file, Class<T> resultType);

	String getDocumentExtension();

	void refreshFile(String fileName, String root, String nodeParentText, List<Map<String, String>> nodes);
	
}
