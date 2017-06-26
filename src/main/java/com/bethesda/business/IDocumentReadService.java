package com.bethesda.business;

import com.bethesda.model.Document;

public interface IDocumentReadService {

	Long getDocumentVersion(String documentName);

	String getDocumentFullPath(String name);
	
	Document getDocumentFullInformation(String name);
}
