package com.testing.service.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;
import java.util.List;

/**
 * To describe a collections of currently available {@link test.smoketest.test.TestSuite}s.
 * Created by mikhail kutuzov on 05.08.2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TestSuitDescriptions {
    @XmlElement
    private Date updated;
    @XmlElement
    private List<TestSuitDescription> descriptions;

    /**
     * When these {@link test.smoketest.test.TestSuite}s became a part of a testing system.
     * @return
     */
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * A collections of {@link test.smoketest.test.TestSuite}s currently available through the system.
     * @return
     */
    public List<TestSuitDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<TestSuitDescription> descriptions) {
        this.descriptions = descriptions;
    }
}
