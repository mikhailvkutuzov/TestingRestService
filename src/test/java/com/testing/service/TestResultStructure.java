package com.testing.service;

import com.testing.service.entities.TestCaseResult;
import com.testing.service.entities.TestSuitResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static com.testing.service.TestingService.BASE_URI;
import static org.junit.Assert.assertEquals;

public class TestResultStructure {

    private TestingService server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        server = new GrizzlyTestingService();
        server.start(GetTestSuitResult.class);
        Client c = ClientBuilder.newClient();
        target = c.target(String.format(BASE_URI, 8181));
    }

    @After
    public void tearDown() throws Exception {
        if (server != null) {
            server.stop();
        }
    }

    @Test
    public void xml() {
        TestSuitResult responseMsg = target.path("test-suit-result/xml").request().get(TestSuitResult.class);
        assertEquals(1, responseMsg.getResults().size());
        TestCaseResult r0 = responseMsg.getResults().get(0);
        assertEquals("message", r0.getMessage());
    }


    @Test
    public void json() {
        TestSuitResult responseMsg = target.path("test-suit-result/json").request().get(TestSuitResult.class);
        assertEquals(1, responseMsg.getResults().size());
        TestCaseResult r0 = responseMsg.getResults().get(0);
        assertEquals("message", r0.getMessage());
    }


}
