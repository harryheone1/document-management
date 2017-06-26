package com.bethesda.business.impl.dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bethesda.business.dictionary.cache.DictionaryCache;
import com.bethesda.model.Document;
import com.bethesda.model.DocumentWrapper;
import com.bethesda.properties.DocumentManagementConstants;

public enum DictionnaryType {

	CLIENT {
		@Override
		protected void buildExtraNodeElement(DocumentWrapper document, Map<String, String> baseNode) {
			// Do Nothing
		}

		@Override
		public String getFileName() {
			return DocumentManagementConstants.getClientDictionaryName();
		}

		@Override
		public void updateCache(List<DocumentWrapper> documents, Boolean flush) {
			DictionaryCache.instance().updateClientCache(documents, flush);
			
		}
	},
	SERVER {

		@Override
		protected void buildExtraNodeElement(DocumentWrapper document, Map<String, String> baseNode) {
			baseNode.put("DIRECTORY", document.getDirectory() + "/" + document.getFileName());
		}

		@Override
		public String getFileName() {
			return DocumentManagementConstants.getServerDictionaryName();
		}

		@Override
		public void updateCache(List<DocumentWrapper> documents, Boolean flush) {
			DictionaryCache.instance().updateServerCache(documents, flush);
		}
	};

	public String getRootElment() {
		return "documents";
	}
	
	public String getNodeParentElmentText() {
		return "document";
	}

	public List<Map<String, String>> buildAllElements(List<DocumentWrapper> currentDocuments) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		currentDocuments.stream().filter(document -> document != null && document.getDocument() != null)
								 .map(document -> buildBaseNodeElement(document))
								 .forEach(nodes -> result.add(nodes));
		return result;
	}
	private Map<String, String> buildBaseNodeElement(DocumentWrapper document) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("DOCUMENT_NAME", document.getDocumentName());
		result.put("VERSION", String.valueOf(document.getVersion()));
		result.put("LENGTH", String.valueOf(document.getData() != null ? document.getData().length : 0));
		buildExtraNodeElement(document, result);
		return result;
	}

	protected abstract void buildExtraNodeElement(DocumentWrapper document, Map<String, String> baseNode);
	
	public abstract String getFileName();

	public abstract void updateCache(List<DocumentWrapper> documents, Boolean flush);
}
