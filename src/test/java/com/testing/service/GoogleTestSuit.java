package com.testing.service;

import test.smoketest.test.TestCase;
import test.smoketest.test.TestSuite;

import java.util.ArrayList;
import java.util.List;

/**
 * This {@link TestSuite} has been done for a test.
 * Created by mikhail kutuzov on 06.07.2017.
 */
public class GoogleTestSuit extends TestSuite {
    @Override
    public String getName() {
        return "GoogleTestSuit";
    }

    @Override
    public String getDescription() {
        return "just to call one simple test case";
    }

    @Override
    public List<TestCase> getTestCases() {
        List<TestCase> google = new ArrayList<>(1);
        google.add(new GoogleTestCase());
        return google;
    }
}
