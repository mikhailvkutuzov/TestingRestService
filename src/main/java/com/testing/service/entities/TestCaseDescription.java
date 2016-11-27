package com.testing.service.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by mikhail.kutuzov on 27.11.2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TestCaseDescription {
    @XmlElement
    private String name;
    @XmlElement
    private String description;

    public TestCaseDescription() {
    }

    public TestCaseDescription(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
