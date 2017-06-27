package com.bethesda.common.properties.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bethesda.common.properties.DictonnaryProperties;
import com.bethesda.common.properties.DocumentProperties;

@Component
public class PropertiesServiceImpl implements IPropertiesService {

	@Autowired
	private DocumentProperties documentProperties;

	@Autowired
	private DictonnaryProperties dictonnaryProperties;

	@Override
	public String getClientDictionaryFileFullName() {
		return dictonnaryProperties.getClient().getDictionnaryFileFullName();
	}

	@Override
	public String getServerDictionaryFileFullName() {
		return dictonnaryProperties.getServer().getDictionnaryFileFullName();
	}

	@Override
	public String getDcoumentFileBasePath() {
		return documentProperties.getDirectory();
	}

	@Override
	public String getClientDictionnaryFileRootElement() {
		return dictonnaryProperties.getClient().getRoot();
	}

	@Override
	public String getServerDictionnaryFileRootElement() {
		return dictonnaryProperties.getServer().getRoot();
	}

	@Override
	public String getClientDictionnaryFileParentElement() {
		return dictonnaryProperties.getClient().getParent();
	}

	@Override
	public String getServerDictionnaryFileParentElement() {
		return dictonnaryProperties.getServer().getParent();
	}

	@Override
	public List<String> getClientDictionaryNodeList() {
		return dictonnaryProperties.getClient().getElements().stream().map(element -> element.getName())
				.collect(Collectors.toList());
	}

	@Override
	public List<String> getServerDictionaryNodeList() {
		return dictonnaryProperties.getServer().getElements().stream().map(element -> element.getName())
				.collect(Collectors.toList());
	}

}
