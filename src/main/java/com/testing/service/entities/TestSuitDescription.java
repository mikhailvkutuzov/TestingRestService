package com.testing.service.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * To describe a {@link test.smoketest.test.TestSuite}
 * Created by mikhail kutuzov on 05.08.2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TestSuitDescription {
    @XmlElement
    private String name;
    @XmlElement
    private String briefDescription;
    @XmlElement
    private String description;

    public TestSuitDescription(String name,String briefDescription, String description) {
        this.name = name;
        this.briefDescription = briefDescription;
        this.description = description;
    }

    public TestSuitDescription() {
    }

    /**
     * Get {@link test.smoketest.test.TestSuite}'s name as it will be present on a web server.
     * @return a name to be used in {@link test.smoketest.test.TestSuite} call
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get {@link test.smoketest.test.TestSuite}'s description to have a clue about its aims.
     * @return a {@link test.smoketest.test.TestSuite} description.
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }
}
