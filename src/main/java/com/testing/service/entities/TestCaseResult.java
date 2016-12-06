package com.testing.service.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Created by mikhail.kutuzov on 27.11.2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TestCaseResult {
    @XmlElement
    private String state;
    @XmlElement
    private TestCaseDescription description;
    @XmlElement
    private String message;
    @XmlElement
    private int seconds;
    @XmlElementWrapper
    private List<String> stackTrace;

    public TestCaseResult() {
    }

    public TestCaseResult(TestCaseDescription description, boolean error, String message, List<String> stackTrace, int seconds) {
        this.description = description;
        this.message = message;
        this.state = error ? "ERROR" : "SUCCESS";
        this.seconds = seconds;
        this.stackTrace = stackTrace;
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

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public List<String> getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(List<String> stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "TestCaseResult{" +
                "description=" + description +
                ", message='" + message + '\'' +
                ", seconds=" + seconds +
                ", stackTrace=" + stackTrace +
                '}';
    }
}
