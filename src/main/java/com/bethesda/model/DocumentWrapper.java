package com.bethesda.model;

public class DocumentWrapper {

	private Document document;
	/** The directory. */
	private String directory;
	
	/** The file name. */
	private String fileName;

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

	/**
	 * Gets the directory.
	 *
	 * @return the directory
	 */
	public String getDirectory() {
		return directory;
	}

	/**
	 * Sets the directory.
	 *
	 * @param directory the new directory
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String extractFullName() {
		return directory;
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
