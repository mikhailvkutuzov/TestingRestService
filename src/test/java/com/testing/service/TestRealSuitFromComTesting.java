package com.testing.service;

import com.testing.service.entities.TestCaseResult;
import com.testing.service.entities.TestSuitResult;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Run a web service and use {@link Client} object to test that web service.
 * There is one {@link test.smoketest.test.TestSuite} in a class path at test phase and it will be called to be execute.
 * Created by mikhail.kutuzov on 27.11.2016.
 */
public class TestRealSuitFromComTesting {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        System.setProperty("urlToBeTested", "http://google.com/");
        server = ServiceManager.start(TestCaseResourcesComTesting.class);
        Client c = ClientBuilder.newClient();
        target = c.target(ServiceManager.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void negative() throws Exception {
        TestSuitResult response = target.path("test-suit/chrome/ABRVALG").request().get(TestSuitResult.class);
        assertTrue(response.getResults().size() == 1);
        TestCaseResult r0 = response.getResults().get(0);
        assertEquals("No such a test suit has been found", r0.getMessage());
        response = target.path("test-suit/chrome/GoogleTestSuit").request().get(TestSuitResult.class);
        assertTrue(response.getResults().size() == 1);
        r0 = response.getResults().get(0);
        assertEquals("", r0.getMessage());
    }

}
