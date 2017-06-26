package com.bethesda.business.impl.dictionary;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bethesda.model.Document;
import com.bethesda.model.DocumentWrapper;
import com.bethesda.model.xml.ClientDocumentResponse;
import com.bethesda.model.xml.ServerDocumentResponse;
import com.bethesda.xml.repository.IDocumentAccessRepository;

@Service
public class DictionnaryFileService {

	@Autowired
	private IDocumentAccessRepository documentAccessRepository;

	public void updateDocumentVersionOfDictionnary() {
		//TODO read/write lock
	}
	
	public void deleteDocumentOfDictionary() {
		//TODO read/write lock
	}
	
	public ServerDocumentResponse getCompleteServerDictionary(File file) {
		return documentAccessRepository.extractDocumentFromFile(file, ServerDocumentResponse.class);
	}
	
	public ClientDocumentResponse getCompleteClientDictionary(File file) {
		return documentAccessRepository.extractDocumentFromFile(file, ClientDocumentResponse.class);
	}

	public void rebuildDictionary(List<DocumentWrapper> currentDocuments) {
		// We don't need to consider multi-thread modify the same file, because there is no multi-thread, this method is called when application startup.
		// If one day it could be called by endpoint, we need to reconsider multi-thread issue, ReentrantReadWriteLock is a pretty good solution of it 
		for (DictionnaryType type : DictionnaryType.values()) {
			rebuildDictionnaryService(currentDocuments, type);
		}
	}

	private void rebuildDictionnaryService(List<DocumentWrapper> currentDocuments, DictionnaryType type) {
		if (currentDocuments != null) {
			documentAccessRepository.refreshFile(type.getFileName(), type.getRootElment(), type.getNodeParentElmentText(), type.buildAllElements(currentDocuments));
			type.updateCache(currentDocuments, true);
			
		}
	}

}
