package com.testing.service;

import com.testing.service.entities.TestCaseDescription;
import com.testing.service.entities.TestCaseResult;
import com.testing.service.entities.TestSuitResult;
import test.smoketest.core.WebBrowsers;
import test.smoketest.test.TestAndTestResult;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/test-suit")
public class TestCaseResources {
    private TestRetriever retriever;
    private TestSuitResult none;


    public TestCaseResources() {
        this.retriever = new TestSuitesInBookScriptorCache();

        List<TestCaseResult> cases = new ArrayList<>(1);
        none = new TestSuitResult(cases);
        TestCaseDescription tcd = new TestCaseDescription("", "");
        cases.add(new TestCaseResult(tcd, "No such a test suit has been found", 0));
    }

    @GET
    @Path("{browser}/{test-suit}")
    @Produces(MediaType.APPLICATION_JSON)
    public TestSuitResult testSuitByName(@PathParam(value = "browser") String browser, @PathParam(value = "test-suit") String testSuitName) {
        try {
            List<TestAndTestResult> tuples = retriever.get(testSuitName).action(WebBrowsers.Chrome, "http://bookscriptor.ru/");
            List<TestCaseResult> results = tuples.stream()
                    .map(t ->
                            new TestCaseResult(
                                    new TestCaseDescription(t.getTest().getName(),
                                            t.getTest().getDescription()),
                                    t.getResult().getMessage(),
                                    t.getResult().getTime()
                            )
                    )
                    .collect(Collectors.toList());
            return new TestSuitResult(results);

        } catch (TestRetriever.NoSuchATestSuit e) {
            return none;
        }
    }

}
