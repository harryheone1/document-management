package com.bethesda.business.service.dictionary;

import com.bethesda.model.Document;

public interface IDictionnaryInfoReadService {

	String getDocumentFullPathFromServiceDictionnary(String documentName);

	Long getDocumentVersionFromServiceDictionnary(String documentName);

	Document getFullDocumentInformationFromSvcDictionnary(String documentName);
}
