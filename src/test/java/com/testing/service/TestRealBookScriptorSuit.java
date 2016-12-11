package com.testing.service;

import com.testing.service.entities.TestCaseResult;
import com.testing.service.entities.TestSuitResult;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.smoketest.utils.FileFromResource;
import test.smoketest.utils.SingleFileByPath;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by mikhail.kutuzov on 27.11.2016.
 */
public class TestRealBookScriptorSuit {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        server = ServiceManager.start(TestCaseResources.class);
        Client c = ClientBuilder.newClient();
        target = c.target(ServiceManager.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void xml() throws Exception {
        TestSuitResult response = target.path("test-suit/chrome/ABRVALG").request().get(TestSuitResult.class);
        assertTrue(response.getResults().size() == 1);
        TestCaseResult r0 = response.getResults().get(0);
        assertEquals("No such a test suit has been found", r0.getMessage());

        response = target.path("test-suit/chrome/TC03OrderForms").request().get(TestSuitResult.class);
        assertTrue(response.getResults().size() >= 1);
    }

}
