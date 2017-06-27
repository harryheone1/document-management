package com.bethesda.entry.endpoint;

import org.springframework.web.bind.annotation.RestController;

import com.bethesda.business.DictionnaryBusinessCenter;
import com.bethesda.business.DocumentManagementWorkflow;
import com.bethesda.common.error.ErrorInfoEexception;
import com.bethesda.common.error.ErrorInfoEnum;
import com.bethesda.model.xml.ClientDocumentResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class DocumentEndPoint {

	@Autowired
	DictionnaryBusinessCenter dictionnaryFileService;
	
	@Autowired
	DocumentManagementWorkflow documentManagementWorkflow;

	@RequestMapping(value = "/api/resource", method = RequestMethod.GET, produces = "application/xml")
	public ClientDocumentResponse resource() throws ErrorInfoEexception {
		return dictionnaryFileService.getClientDictionaryAsResponse();
	}

	@RequestMapping(value = "/api/document/{name}/version/{version}", method = RequestMethod.GET)
	public ResponseEntity<Resource> download(@PathVariable String name, @PathVariable Long version) throws ErrorInfoEexception {
		if (StringUtils.isEmpty(name)) {
			throw new ErrorInfoEexception(ErrorInfoEnum.DOCUMENT_NAME_NULL);
		} else if (StringUtils.isEmpty(version)) {
			throw new ErrorInfoEexception(ErrorInfoEnum.DOCUMENT_VERSION_NULL);
		}
		return ResponseEntity.ok()
	            .header("Content-Disposition", "attachment; filename=" + name + "-" + version + ".xml")
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .body(documentManagementWorkflow.getDownloadDocument(name, version));
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
