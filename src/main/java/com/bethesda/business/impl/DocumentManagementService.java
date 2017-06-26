package com.bethesda.business.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bethesda.business.impl.dictionary.DictionnaryFileService;
import com.bethesda.business.impl.document.FileScannerService;
import com.bethesda.model.DocumentWrapper;

@Service
public class DocumentManagementService {

	@Autowired
	FileScannerService fileScanner;
	
	@Autowired
	DictionnaryFileService dictionnaryService;

	public void refreshDictionary() {
		// 1 - Scan All xml files
    	List<DocumentWrapper> documents;
		try {
			documents = fileScanner.getAllDocument();
			// 2 - update server.xml and client.xml
	    	dictionnaryService.rebuildDictionary(documents);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
