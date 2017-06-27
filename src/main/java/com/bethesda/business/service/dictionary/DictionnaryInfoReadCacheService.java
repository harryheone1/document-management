package com.bethesda.business.service.dictionary;

import org.springframework.stereotype.Component;

import com.bethesda.common.cache.DictionaryCache;
import com.bethesda.common.cache.DocumentCache;
import com.bethesda.model.Document;

@Component
public class DictionnaryInfoReadCacheService implements IDictionnaryInfoReadService {

	@Override
	public String getDocumentFullPathFromServiceDictionnary(String documentName) {
		DocumentCache cache = DictionaryCache.instance().getDocumentSummary(documentName);
		if (cache != null) {
			return cache.getFullFileName();
		}
		return null;
	}

	@Override
	public Long getDocumentVersionFromServiceDictionnary(String documentName) {
		return DictionaryCache.instance().getVersion(documentName);
	}

	@Override
	public Document getFullDocumentInformationFromSvcDictionnary(String documentName) {
		DocumentCache cache = DictionaryCache.instance().getDocumentSummary(documentName);
		Document document = new Document();
		document.setDocumentName(documentName);
		document.setVersion(cache.getVersion());
		return document;
	}

}
