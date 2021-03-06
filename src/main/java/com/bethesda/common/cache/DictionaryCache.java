package com.bethesda.common.cache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.bethesda.model.DocumentWrapper;

public class DictionaryCache {

	private static DictionaryCache INSTANCE = new DictionaryCache();

	private final Map<String, DocumentCache> SERVER_INSTANCE = new ConcurrentHashMap<String, DocumentCache>();

	private final Map<String, DocumentCache> CLIENT_INSTANCE = new ConcurrentHashMap<String, DocumentCache>();
	
	private DictionaryCache() {
	}

	public static DictionaryCache instance() {
		return INSTANCE;
	}

	public void updateCache(Map<String, DocumentCache> currentCache, List<DocumentWrapper> documents, Boolean flush) {
		if (flush) {
			currentCache.clear();
		}
		documents.stream().forEach(document -> {
			currentCache.put(document.getDocumentName(), new DocumentCache(document.getVersion(), document.extractFullName()));
		});
	}
	
	public void updateServerCache(List<DocumentWrapper> documents, Boolean flush) {
		updateCache(SERVER_INSTANCE, documents, flush);
	}

	public void updateClientCache(List<DocumentWrapper> documents, Boolean flush) {
		updateCache(CLIENT_INSTANCE, documents, flush);
	}

	public Long getVersion(String documentName) {
		DocumentCache document = SERVER_INSTANCE.get(documentName);
		if (document != null) {
			return document.getVersion();
		}
		return null;
	}

	public DocumentCache getDocumentSummary(String documentName) {
		return SERVER_INSTANCE.get(documentName);
	}
	
}
