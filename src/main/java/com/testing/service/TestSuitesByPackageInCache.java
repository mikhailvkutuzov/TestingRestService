package com.testing.service;

import com.testing.service.entities.TestSuitDescription;
import com.testing.service.entities.TestSuitDescriptions;
import test.smoketest.test.TestSuite;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class is made to get {@link TestSuite} inheritors from the specific package in a class path and
 * put their instances in a map [{@link TestSuite}.class.SimpleName - {@link TestSuite}]
 * Created by mikhail.kutuzov on 26.11.2016.
 */
public class TestSuitesByPackageInCache implements TestRetriever {
    private Map<String, TestSuite> testSuites;
    private TestSuitDescriptions suitDescriptions;

    public TestSuitesByPackageInCache(String testingPackage) {
        try {
            List<Class> classes = ClassesFromPackage.getClasses(testingPackage);

            testSuites = classes.stream()
                    .filter(c -> TestSuite.class.isAssignableFrom(c))
                    .map(c -> {
                        try {
                            return (TestSuite)c.newInstance();
                        } catch (InstantiationException | IllegalAccessException e) {
                            throw new TestSuitCreation(e);
                        }
                    })
                    .collect(Collectors.toMap(t -> t.getClass().getSimpleName(), t -> t));
            suitDescriptions = new TestSuitDescriptions();
            suitDescriptions.setUpdated(new Date());
            suitDescriptions.setDescriptions(testSuites.values().stream()
                    .map(s -> new TestSuitDescription(s.getName(), s.getDescription()))
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            throw new CouldNotFindClasses(e);
        }
    }

    @Override
    public TestSuite get(String name) throws NoSuchATestSuit {
        TestSuite test = testSuites.get(name);
        if(test != null) {
            return test;
        } else {
            throw new NoSuchATestSuit();
        }
    }

    @Override
    public TestSuitDescriptions available() {
        return suitDescriptions;
    }

    public static final class TestSuitCreation extends RuntimeException {
        public TestSuitCreation(Throwable cause) {
            super(cause);
        }
    }

    public static final class CouldNotFindClasses extends RuntimeException {
        public CouldNotFindClasses(Throwable cause) {
            super(cause);
        }
    }

}
