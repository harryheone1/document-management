package com.bethesda.xml.repository.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Repository;

import com.bethesda.xml.repository.IDocumentAccessRepository;

@Repository
public class DocumentAccessRepository implements IDocumentAccessRepository {

	@Override
	public <T> T extractDocumentFromFile(File file, Class<T> resultType) {
		JAXBContext jaxbContext;
		try {
			//TODO write lock
			jaxbContext = JAXBContext.newInstance(resultType);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return resultType.cast(jaxbUnmarshaller.unmarshal(file));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void updateDocmentVersion(String documentName, String version) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDocumentExtension() {
		return "xml";
	}

	@Override
	public void refreshFile(String fileName, String root, String nodeParentText, List<Map<String, String>> nodes) {
		File xmlFile = new File(fileName);
		org.dom4j.Document xmlDocument = null;
		try {
			if (xmlFile.exists() && !xmlFile.isDirectory()) {
				SAXReader saxReader = new SAXReader();
				xmlDocument = saxReader.read(xmlFile);
				xmlDocument.clearContent();
			} else {
				xmlDocument = DocumentHelper.createDocument();
			}
			Element documents = xmlDocument.addElement(root);
			nodes.stream().forEach(nodeParents -> {
				Element documentElement = documents.addElement(nodeParentText);
				nodeParents.keySet().stream().filter(nodeParent -> nodeParent != null).forEach(nodeParent -> {
					Element name = documentElement.addElement(nodeParent);
					name.setText(nodeParents.get(nodeParent));
				});
			});

			Writer fileWriter = new FileWriter(fileName);
			XMLWriter xmlWriter = new XMLWriter(fileWriter);
			xmlWriter.write(xmlDocument);
			xmlWriter.close();
		} catch (IOException | DocumentException e) {
			System.out.println(e.getMessage());
		}

	}

}
