package com.bethesda.business.impl.document;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bethesda.business.IDocumentReadService;
import com.bethesda.model.Document;
import com.bethesda.model.DocumentWrapper;
import com.bethesda.xml.repository.IDocumentAccessRepository;

@Service
public class DocumentFileService {

	@Autowired
	private IDocumentAccessRepository documentAccessRepository;

	@Autowired
	private IDocumentReadService documentReadService;

	public Boolean isDocumentExtensionValid(Path path) {
		PathMatcher matcher = FileSystems.getDefault().getPathMatcher(getValidExtensionName());

		if (matcher.matches(path)) {
			return true;
		}
		return false;

	}

	public DocumentWrapper getCompleteDocument(File file) {
		Document document = documentAccessRepository.extractDocumentFromFile(file, Document.class);
		DocumentWrapper wrapper = new DocumentWrapper(document);
		if (document != null && file != null) {
			//wrapper.setFileName(file.getName());
			wrapper.setDirectory(file.getPath());
		}
		return wrapper;
	}

	public Boolean isVersionUpToDate(String documentName, Long version) {
		if (documentName == null || version == null) {
			return false;
		}

		if (version.equals(documentReadService.getDocumentVersion(documentName))) {
			return true;
		}
		return false;
	}

	public String getValidExtensionName() {
		return "glob:*." + documentAccessRepository.getDocumentExtension();
	}

	public File getDocumentFile(String name) {
		String fullPath = documentReadService.getDocumentFullPath(name);
		if (fullPath != null) {
			return new File(fullPath);
		}
		return null;
	}
	// validate version
	// get docment
}
