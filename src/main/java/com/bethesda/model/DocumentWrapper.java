package com.bethesda.model;

public class DocumentWrapper {

	private Document document;

	private String fullPath;
	
	public DocumentWrapper(Document document) {
		super();
		this.document = document;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}


	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String extractFullName() {
		return fullPath;
	}
	
	public String getDocumentName() {
		return this.document.getDocumentName();
	}

	public void setDocumentName(String documentName) {
		this.document.setDocumentName(documentName);
	}

	public Long getVersion() {
		return this.document.getVersion();
	}
	
	public void setVersion(Long version) {
		this.document.setVersion(version);
	}
	
	public byte[] getData() {
		return this.document.getData();
	}
	
	public void setData(byte[] data) {
		this.document.setData(data);
	}
}
