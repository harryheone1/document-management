package com.bethesda.business;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bethesda.business.repository.IDocumentAccessRepo;
import com.bethesda.business.repository.xml.XmlDocumentAccessRepo;
import com.bethesda.business.service.dictionary.IDictionnaryInfoReadService;
import com.bethesda.common.error.ErrorInfoEexception;
import com.bethesda.common.properties.service.IPropertiesService;
import com.bethesda.model.Document;
import com.bethesda.model.DocumentWrapper;

@Service
public class DocumentBusinessCenter {

	private static final Logger logger = LoggerFactory.getLogger(XmlDocumentAccessRepo.class);

	@Autowired
	private IDocumentAccessRepo documentAccessRepository;

	@Autowired
	private IDictionnaryInfoReadService dictionnaryInfoReadService;
	
	@Autowired
	private IPropertiesService propertiesService;

	public Boolean isDocumentExtensionValid(Path path) {
		PathMatcher matcher = FileSystems.getDefault().getPathMatcher(getValidExtensionName());

		if (matcher.matches(path)) {
			return true;
		}
		return false;

	}

	public List<DocumentWrapper> getAllDocumentAsJavaObject() throws IOException {
		try (Stream<Path> paths = Files.walk(Paths.get(propertiesService.getDcoumentFileBasePath()))) {
		    return paths
		        .filter(Files::isRegularFile)
		        //.filter(path -> documentFileService.isDocumentExtensionValid(path))
		        .map(path -> {
					try {
						return getDocumentAsJavaObject(path.toFile());
					} catch (ErrorInfoEexception e) {
						logger.error("Some error happened when read xml file to java", e);
					}
					return null;
				})
		        .filter(document -> document != null)
		        .collect(Collectors.toList());
		} 
	}

	public DocumentWrapper getDocumentAsJavaObject(File file) throws ErrorInfoEexception {
		Document document = documentAccessRepository.extractDocumentFromFile(file, Document.class);
		DocumentWrapper wrapper = new DocumentWrapper(document);
		if (document != null && file != null) {
			wrapper.setFullPath(file.getPath());
		}
		return wrapper;
	}

	public Boolean isVersionUpToDate(String documentName, Long version) {
		if (documentName == null || version == null) {
			return false;
		}

		if (version.equals(dictionnaryInfoReadService.getDocumentVersionFromServiceDictionnary(documentName))) {
			return true;
		}
		return false;
	}

	public String getValidExtensionName() {
		return "glob:*." + documentAccessRepository.getDocumentExtension();
	}

	public File getDocumentPathInfo(String name) {
		String fullPath = dictionnaryInfoReadService.getDocumentFullPathFromServiceDictionnary(name);
		if (fullPath != null) {
			return new File(fullPath);
		}
		return null;
	}
}
