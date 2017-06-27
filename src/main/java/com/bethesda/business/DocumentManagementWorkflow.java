package com.bethesda.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;

import com.bethesda.common.error.ErrorInfoEexception;
import com.bethesda.common.error.ErrorInfoEnum;
import com.bethesda.model.DocumentWrapper;

@Component
public class DocumentManagementWorkflow {

	private static final Logger logger = LoggerFactory.getLogger(DocumentManagementWorkflow.class);

	@Autowired
	DocumentBusinessCenter documentBusinessCenter;

	@Autowired
	DictionnaryBusinessCenter dictionnarydocumentBusinessCenter;
	
	public void refreshDictionary() throws ErrorInfoEexception {
		// 1 - Scan All xml files
    	List<DocumentWrapper> documents;
		try {
			documents = documentBusinessCenter.getAllDocumentAsJavaObject();
			// 2 - update server.xml and client.xml
			dictionnarydocumentBusinessCenter.rebuildDictionary(documents);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public InputStreamResource getDownloadDocument(String name, Long version) throws Exception {
		if (!documentBusinessCenter.isVersionUpToDate(name, version)) {
			logger.error("Error happened when download xml file, file not exist or expired version, please rerun resouce endpoint to update your dictionnary");
			throw new ErrorInfoEexception(ErrorInfoEnum.UNKOWN_ERROR);
		}
		File documentFile = documentBusinessCenter.getDocumentPathInfo(name);
		return new InputStreamResource(new FileInputStream(documentFile));

		
	}
}
