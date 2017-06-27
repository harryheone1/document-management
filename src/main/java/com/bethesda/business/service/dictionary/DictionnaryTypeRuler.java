package com.bethesda.business.service.dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bethesda.common.cache.DictionaryCache;
import com.bethesda.common.properties.service.IPropertiesService;
import com.bethesda.model.DocumentWrapper;

public enum DictionnaryTypeRuler {

	CLIENT {
		@Override
		public String getFileName(IPropertiesService propertiesService) {
			return propertiesService.getClientDictionaryFileFullName();
		}

		@Override
		public void updateCache(List<DocumentWrapper> documents, Boolean flush) {
			DictionaryCache.instance().updateClientCache(documents, flush);

		}

		@Override
		public List<String> getDictionnaryNodeElementList(IPropertiesService propertiesService) {
			return propertiesService.getClientDictionaryNodeList();
			
		}

		@Override
		public String getRootElment(IPropertiesService propertiesService) {
			return propertiesService.getClientDictionnaryFileRootElement();
		}

		@Override
		public String getNodeParentElmentText(IPropertiesService propertiesService) {
			return propertiesService.getClientDictionnaryFileParentElement();
		}
	},
	SERVER {
		@Override
		public String getFileName(IPropertiesService propertiesService) {
			return propertiesService.getServerDictionaryFileFullName();
		}

		@Override
		public void updateCache(List<DocumentWrapper> documents, Boolean flush) {
			DictionaryCache.instance().updateServerCache(documents, flush);
		}

		@Override
		public List<String> getDictionnaryNodeElementList(IPropertiesService propertiesService) {
			return propertiesService.getServerDictionaryNodeList();
			
		}

		@Override
		public String getRootElment(IPropertiesService propertiesService) {
			return propertiesService.getServerDictionnaryFileRootElement();
		}

		@Override
		public String getNodeParentElmentText(IPropertiesService propertiesService) {
			return propertiesService.getServerDictionnaryFileParentElement();
		}
	};

	public abstract List<String> getDictionnaryNodeElementList(IPropertiesService propertiesService);

	public abstract String getFileName(IPropertiesService propertiesService);

	public abstract void updateCache(List<DocumentWrapper> documents, Boolean flush);

	public abstract String getRootElment(IPropertiesService propertiesService);

	public abstract String getNodeParentElmentText(IPropertiesService propertiesService);

	public List<Map<String, String>> buildAllElements(IPropertiesService propertiesService, List<DocumentWrapper> currentDocuments) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		currentDocuments.stream().filter(document -> document != null && document.getDocument() != null)
				.map(document -> buildBaseNodeElement(propertiesService, document)).forEach(nodes -> result.add(nodes));
		return result;
	}

	private Map<String, String> buildBaseNodeElement(IPropertiesService propertiesService, DocumentWrapper document) {
		Map<String, String> result = new HashMap<String, String>();
		List<String> nodeList = getDictionnaryNodeElementList(propertiesService);
		if (nodeList != null) {
			nodeList.stream().forEach(elementName -> {
				result.put(elementName, ElementType.valueOf(elementName).getValue(document));
			});
		}
		return result;
	}

	private enum ElementType {
		DOCUMENT_NAME {
			@Override
			public String getValue(DocumentWrapper document) {
				return document.getDocumentName();
			}
		},		
		VERSION {
			@Override
			public String getValue(DocumentWrapper document) {
				return String.valueOf(document.getVersion());
			}
		},
		LENGTH {
			@Override
			public String getValue(DocumentWrapper document) {
				return String.valueOf(document.getData() != null ? document.getData().length : 0);
			}
		},
		PATH_TO_FILE {
			@Override
			public String getValue(DocumentWrapper document) {
				return document.getFullPath();
			}
		};

		public abstract String getValue(DocumentWrapper document);
	}

}
