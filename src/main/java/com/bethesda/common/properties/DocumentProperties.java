package com.bethesda.common.properties;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("document")
public class DocumentProperties {

	private String directory;
	private String extention;
	private String root;
    private List<Element> elements = new ArrayList<>();

    public static class Element {
        private String name;
        private String type;

        //getters and setters

        @Override
        public String toString() {
            return "Element{" +
                    "name='" + name + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
    }

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
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

	public List<Element> getElements() {
		if (elements == null) {
			elements = new ArrayList<>();
		}
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

}
