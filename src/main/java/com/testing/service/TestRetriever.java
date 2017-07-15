package com.testing.service;

import test.smoketest.test.TestSuite;

/**
 * To get somehow a {@link TestSuite} by name.
 * Created by mikhail.kutuzov on 26.11.2016.
 */
public interface TestRetriever {

    TestSuite get(String name) throws NoSuchATestSuit;

    final class NoSuchATestSuit extends Exception {}
}
