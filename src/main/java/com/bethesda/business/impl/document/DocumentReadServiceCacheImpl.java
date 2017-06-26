package com.bethesda.business.impl.document;

import org.springframework.stereotype.Component;

import com.bethesda.business.IDocumentReadService;
import com.bethesda.business.dictionary.cache.DictionaryCache;
import com.bethesda.business.dictionary.cache.DocumentCache;
import com.bethesda.model.Document;

@Component
public class DocumentReadServiceCacheImpl implements IDocumentReadService {

	@Override
	public String getDocumentFullPath(String documentName) {
		DocumentCache cache = DictionaryCache.instance().getDocumentSummary(documentName);
		if (cache != null) {
			return cache.getFullFileName();
		}
		return null;
	}

	@Override
	public Long getDocumentVersion(String documentName) {
		return DictionaryCache.instance().getVersion(documentName);
	}

	@Override
	public Document getDocumentFullInformation(String documentName) {
		DocumentCache cache = DictionaryCache.instance().getDocumentSummary(documentName);
		Document document = new Document();
		document.setDocumentName(documentName);
		document.setVersion(cache.getVersion());
		return document;
	}

}
