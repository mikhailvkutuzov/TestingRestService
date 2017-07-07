package com.testing.service;

import test.smoketest.core.Navigator;
import test.smoketest.core.ProjectNavigator;
import test.smoketest.core.WebBrowsers;
import test.smoketest.test.TestCase;

/**
 * This {@link TestCase} is made for test purpose.
 * Created by mikhail kutuzov on 07.07.2017.
 */
public class GoogleTestCase extends TestCase {
    @Override
    public String getName() {
        return "ClickLogotype";
    }

    @Override
    public String getDescription() {
        return "just start browser at google.com and click a logotype";
    }

    @Override
    protected void test(WebBrowsers browser, String url) {
        Navigator navigator = new ProjectNavigator(WebBrowsers.Chrome, url);
        try {
            GooglePage mainPage = navigator.open(GooglePage.class);
            mainPage.getLogo().click();
        } finally {
            navigator.close();
        }
    }
}
