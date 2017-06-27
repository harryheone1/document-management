package com.bethesda.business.repository.xml;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bethesda.business.repository.IDocumentAccessRepo;
import com.bethesda.common.error.ErrorInfoEexception;
import com.bethesda.common.error.ErrorInfoEnum;

@Repository
public class XmlDocumentAccessRepo implements IDocumentAccessRepo {

	private static final Logger logger = LoggerFactory.getLogger(XmlDocumentAccessRepo.class);

	@Override
	public <T> T extractDocumentFromFile(File file, Class<T> resultType) throws ErrorInfoEexception {
		JAXBContext jaxbContext;
		try {
			// TODO write lock
			jaxbContext = JAXBContext.newInstance(resultType);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return resultType.cast(jaxbUnmarshaller.unmarshal(file));
		} catch (JAXBException e) {
			logger.error("Error happened when convert xml to java, please check them", e);
			throw new ErrorInfoEexception(ErrorInfoEnum.UNKOWN_ERROR);
		}

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
	public void refreshFile(String fileName, String root, String nodeParentText, List<Map<String, String>> nodes)
			throws ErrorInfoEexception {
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
			logger.error(
					"Error happened when update dictionnary xml file, a lot of possible reason, please check the details",
					e);
			throw new ErrorInfoEexception(ErrorInfoEnum.UNKOWN_ERROR);
		}

	}

}
