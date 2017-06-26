package com.bethesda.business.impl.document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bethesda.model.Document;
import com.bethesda.model.DocumentWrapper;
import com.bethesda.properties.DocumentManagementConstants;

@Component
public class FileScannerService {

	@Autowired
	DocumentFileService documentFileService;

	public List<DocumentWrapper> getAllDocument() throws IOException {
		try (Stream<Path> paths = Files.walk(Paths.get(DocumentManagementConstants.FILE_BASE_PATH))) {
		    return paths
		        .filter(Files::isRegularFile)
		        //.filter(path -> documentFileService.isDocumentExtensionValid(path))
		        .map(path -> documentFileService.getCompleteDocument(path.toFile()))
		        .filter(document -> document != null)
		        .collect(Collectors.toList());
		} 
	}

}
