package com.testing.service.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by mikhail.kutuzov on 27.11.2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TestCaseResult {
    @XmlElement
    private TestCaseDescription description;
    @XmlElement
    private String message;

    public TestCaseResult() {
    }

    public TestCaseResult(TestCaseDescription description, String message) {
        this.description = description;
        this.message = message;
    }

    public TestCaseDescription getDescription() {
        return description;
    }

    public void setDescription(TestCaseDescription description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
