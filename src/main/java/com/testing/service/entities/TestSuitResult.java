package com.testing.service.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by mikhail.kutuzov on 27.11.2016.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TestSuitResult {
    @XmlElement
    private List<TestCaseResult> results;

    public TestSuitResult() {
    }

    public TestSuitResult(List<TestCaseResult> results) {
        this.results = results;
    }

    public List<TestCaseResult> getResults() {
        return results;
    }

    public void setResults(List<TestCaseResult> results) {
        this.results = results;
    }
}
