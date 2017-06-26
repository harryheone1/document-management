package com.bethesda.model.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DOCUMENTS")
public class ClientDocumentResponse {

private List<ClientDocument> documents;
	
	public List<ClientDocument> getDocuments() {
		return documents;
	}

	@XmlElement(name = "DOCUMENT")
	public void setDocuments(List<ClientDocument> documents) {
		this.documents = documents;
	}
}
