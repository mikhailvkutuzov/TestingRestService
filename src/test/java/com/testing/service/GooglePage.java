package com.testing.service;

import org.openqa.selenium.WebDriver;
import test.smoketest.controls.Button;
import test.smoketest.controls.TheSamePageButton;
import test.smoketest.core.PageBase;

/**
 * It is a simple google search page to locate a logotype.
 * Created by mikhail.kutuzov on 19.02.2016.
 */
public class GooglePage extends PageBase {

    public GooglePage(WebDriver driver, String projectUrl) {
        super(driver, projectUrl);
    }

    @Override
    protected String getRelativeUrl() {
        return "";
    }


    public Button getLogo(){
        return new TheSamePageButton(this, getDriver(), ".//*[@id='hplogo']");
    }

}
