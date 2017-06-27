package com.bethesda.common.properties;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.bethesda.common.properties.DocumentProperties.Element;

@Component
@ConfigurationProperties("dictionnary")
public class DictonnaryProperties {

	private DICTIONNARY server;

	private DICTIONNARY client;

	public static class DICTIONNARY {
		private String directory;
		private String name;
		private String extention;
		private String root;
		private String parent;
		private List<Element> elements = new ArrayList<>();

		public String getDictionnaryFileFullName() {
			return directory + "/" + name + "." + extention;
		}
		public String getDirectory() {
			return directory;
		}

		public void setDirectory(String directory) {
			this.directory = directory;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getExtention() {
			return extention;
		}

		public void setExtention(String extention) {
			this.extention = extention;
		}

		public String getRoot() {
			return root;
		}

		public void setRoot(String root) {
			this.root = root;
		}

		public String getParent() {
			return parent;
		}

		public void setParent(String parent) {
			this.parent = parent;
		}

		public List<Element> getElements() {
			return elements;
		}

		public void setElements(List<Element> elements) {
			this.elements = elements;
		}

	}

	public DICTIONNARY getServer() {
		return server;
	}

	public void setServer(DICTIONNARY server) {
		this.server = server;
	}

	public DICTIONNARY getClient() {
		return client;
	}

	public void setClient(DICTIONNARY client) {
		this.client = client;
	}
}
