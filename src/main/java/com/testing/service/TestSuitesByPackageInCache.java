package com.testing.service;

import test.smoketest.test.TestSuite;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mikhail.kutuzov on 26.11.2016.
 */
public class TestSuitesByPackageInCache implements TestRetriever {
    private Map<String, TestSuite> testSuites;

    public TestSuitesByPackageInCache(Package testingPackage) {
        try {
            List<Class> classes = ClassesFromPackage.getClasses(testingPackage.getName());

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
