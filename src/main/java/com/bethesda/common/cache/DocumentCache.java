package com.bethesda.common.cache;

public class DocumentCache {

	public Long version;

	public String fullFileName;

	public DocumentCache(Long version, String fullFileName) {
		super();
		this.version = version;
		this.fullFileName = fullFileName;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getFullFileName() {
		return fullFileName;
	}

	public void setFullFileName(String fullFileName) {
		this.fullFileName = fullFileName;
	}}
