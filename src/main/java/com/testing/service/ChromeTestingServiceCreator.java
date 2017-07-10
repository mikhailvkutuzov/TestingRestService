package com.testing.service;

import org.glassfish.grizzly.http.server.HttpServer;
import test.smoketest.utils.DeterminateExtensionResource;
import test.smoketest.utils.ExecutableResource;
import test.smoketest.utils.ProduceChromeDriver;
import test.smoketest.utils.SingleFileByPath;

import java.io.File;

/**
 * Load chromium driver to run tests on Chrome browser.
 * Created by mikhail.kutuzov on 10.07.17.
 */
public class ChromeTestingServiceCreator extends DelegatingServiceCreator {
    public ChromeTestingServiceCreator(TestingServiceCreator service) {
        super(service);
    }

    @Override
    public HttpServer start(Class component) throws Exception {
        chrome();
        return super.start(component);
    }

    @Override
    public HttpServer start(String packagePath) throws Exception {
        chrome();
        return super.start(packagePath);
    }

    private void chrome(){
        new ProduceChromeDriver(new DeterminateExtensionResource(new ExecutableResource(new SingleFileByPath())))
                .create("chromedriver", new File("chromedriver.exe"));
    }
}
