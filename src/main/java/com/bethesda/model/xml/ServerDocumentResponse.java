package com.bethesda.model.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DOCUMENTS")
public class ServerDocumentResponse {

	private List<ServerDocument> documents;
	
	public List<ServerDocument> getDocuments() {
		return documents;
	}

	@XmlElement(name = "DOCUMENT")
	public void setDocuments(List<ServerDocument> documents) {
		this.documents = documents;
	}
}
