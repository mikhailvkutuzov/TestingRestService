package com.testing.service;

import test.smoketest.test.TestSuite;

/**
 * Created by mikhail.kutuzov on 26.11.2016.
 */
public interface TestRetriever {

    TestSuite get(String name) throws NoSuchATestSuit;

    public static final class NoSuchATestSuit extends Exception {}
}
