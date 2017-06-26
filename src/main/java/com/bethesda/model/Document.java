package com.bethesda.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Document.
 */
@XmlRootElement(name="DOCUMENT")
public class Document {

	/** The name. */
	private String documentName;
	
	/** The version. */
	private Long version;
	
	/** The data. */
	private byte[] data;

	
	/**
	 * Gets the document name.
	 *
	 * @return the document name
	 */
	@XmlElement(name="DOCUMENT_NAME")
	public String getDocumentName() {
		return documentName;
	}

	/**
	 * Sets the document name.
	 *
	 * @param documentName the new document name
	 */
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	@XmlElement(name="VERSION")
	public Long getVersion() {
		return version;
	}
	
	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	public void setVersion(Long version) {
		this.version = version;
	}
	
	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	@XmlElement(name="DATA")
	public byte[] getData() {
		return data;
	}
	
	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(byte[] data) {
		this.data = data;
	}

}
