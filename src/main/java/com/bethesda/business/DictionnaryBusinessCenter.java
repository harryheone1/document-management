package com.bethesda.business;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bethesda.business.repository.IDocumentAccessRepo;
import com.bethesda.business.service.dictionary.DictionnaryTypeRuler;
import com.bethesda.common.error.ErrorInfoEexception;
import com.bethesda.common.properties.service.IPropertiesService;
import com.bethesda.model.DocumentWrapper;
import com.bethesda.model.xml.ClientDocumentResponse;
import com.bethesda.model.xml.ServerDocumentResponse;

@Service
public class DictionnaryBusinessCenter {

	@Autowired
	private IDocumentAccessRepo documentAccessRepository;

	@Autowired
	private IPropertiesService propertiesService;

	public void updateDocumentVersionOfDictionnary() {
		// TODO read/write lock
	}

	public ServerDocumentResponse getServerDictionaryAsResponse(File file) throws ErrorInfoEexception {
		return documentAccessRepository.extractDocumentFromFile(new File(propertiesService.getServerDictionaryFileFullName()), ServerDocumentResponse.class);
	}

	public ClientDocumentResponse getClientDictionaryAsResponse() throws ErrorInfoEexception {
		return documentAccessRepository.extractDocumentFromFile(new File(propertiesService.getClientDictionaryFileFullName()), ClientDocumentResponse.class);
	}

	public void rebuildDictionary(List<DocumentWrapper> currentDocuments) throws ErrorInfoEexception {
		// We don't need to consider multi-thread modify the same file, because
		// there is no multi-thread, this method is called when application
		// startup.
		// If one day it could be called by endpoint, we need to reconsider
		// multi-thread issue, ReentrantReadWriteLock is a pretty good solution
		// of it
		for (DictionnaryTypeRuler type : DictionnaryTypeRuler.values()) {
			rebuildDictionnaryFile(currentDocuments, type);
		}
	}

	private void rebuildDictionnaryFile(List<DocumentWrapper> currentDocuments, DictionnaryTypeRuler type)
			throws ErrorInfoEexception {
		if (currentDocuments != null) {
			documentAccessRepository.refreshFile(type.getFileName(propertiesService),
					type.getRootElment(propertiesService), type.getNodeParentElmentText(propertiesService),
					type.buildAllElements(propertiesService, currentDocuments));
			type.updateCache(currentDocuments, true);

		}
	}

}
