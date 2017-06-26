package com.bethesda.endpoint;

import org.springframework.web.bind.annotation.RestController;

import com.bethesda.business.impl.dictionary.DictionnaryFileService;
import com.bethesda.business.impl.document.DocumentFileService;
import com.bethesda.business.impl.document.FileScannerService;
import com.bethesda.model.xml.ClientDocumentResponse;
import com.bethesda.properties.DocumentManagementConstants;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class DocumentEndPoint {

	@Autowired
	FileScannerService fileScanner;

	@Autowired
	DocumentFileService documentFileService;
	
	@Autowired
	DictionnaryFileService dictionnaryFileService;

	@RequestMapping(value = "/api/resource", method = RequestMethod.GET, produces = "application/xml")
	public ClientDocumentResponse resource() {
		return dictionnaryFileService.getCompleteClientDictionary(new File(DocumentManagementConstants.getClientDictionaryName()));
	}

	@RequestMapping(value = "/api/document/{name}/version/{version}", method = RequestMethod.GET)
	public ResponseEntity<Resource> download(@PathVariable String name, @PathVariable Long version) throws Exception {
		if (!documentFileService.isVersionUpToDate(name, version)) {
			throw new Exception();
		}
		File documentFile = documentFileService.getDocumentFile(name);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(documentFile));

		return ResponseEntity.ok()
	            .contentLength(documentFile.length())
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .body(resource);
	}

	@RequestMapping(value = "/api/document/{name}/version/{version}", method = RequestMethod.PUT)
	public void upload(@PathVariable String name, @PathVariable Long version) {
		// 1 - Check document and version exist in server.xml - write lock
		// 2 - not exist create - update server.xml - write lock
		// 3 - create xml file
		// 4 - exist update - update server.xml - write lock
		// 5 - update xml file
		// 6 - update client - write lo
	}

}
