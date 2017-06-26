package com.bethesda.model.xml;

import javax.xml.bind.annotation.XmlElement;

public class ServerDocument {

	private String documentName;
	
	private Long version;

	private Long length;

	private String path;

	public String getDocumentName() {
		return documentName;
	}

	@XmlElement(name="DOCUMENT_NAME")
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public Long getVersion() {
		return version;
	}

	@XmlElement(name="VERSION")
	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getLength() {
		return length;
	}

	@XmlElement(name="LENGTH")
	public void setLength(Long length) {
		this.length = length;
	}

	public String getPath() {
		return path;
	}

	@XmlElement(name="PATH_TO_FILE")
	public void setPath(String path) {
		this.path = path;
	}

}
