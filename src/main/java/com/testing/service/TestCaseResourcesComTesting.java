package com.testing.service;

import com.testing.service.entities.TestCaseDescription;
import com.testing.service.entities.TestCaseResult;
import com.testing.service.entities.TestSuitResult;
import test.smoketest.core.WebBrowsers;
import test.smoketest.test.TestAndTestResult;
import test.smoketest.test.TestResult;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Path("/test-suit")
public class TestCaseResourcesComTesting {
    private TestRetriever retriever;
    private TestSuitResult none;
    private String urlToBeTested;


    public TestCaseResourcesComTesting() {
        this.retriever = new TestSuitesByPackageInCache(Package.getPackage("com.testing"));

        List<TestCaseResult> cases = new ArrayList<>(1);
        none = new TestSuitResult(cases);
        TestCaseDescription tcd = new TestCaseDescription("", "");
        cases.add(new TestCaseResult(tcd, true, "No such a test suit has been found", new ArrayList<>(0), 0));
        urlToBeTested = System.getProperty("urlToBeTested");
        if (urlToBeTested == null) {
            throw new IllegalArgumentException();
        }
    }

    @GET
    @Path("{browser}/{test-suit}")
    @Produces(MediaType.APPLICATION_JSON)
    public TestSuitResult testSuitByName(@PathParam(value = "browser") String browser, @PathParam(value = "test-suit") String testSuitName) {
        try {
            List<TestAndTestResult> tuples = retriever.get(testSuitName)
                    .action(WebBrowsers.Chrome, urlToBeTested);
            List<TestCaseResult> results = tuples.stream()
                    .map(t ->
                            new TestCaseResult(
                                    new TestCaseDescription(t.getTest().getName(),
                                            t.getResult().isError() ? t.getTest().getDescription() : ""),
                                    t.getResult().isError(),
                                    message(t.getResult()),
                                    stackTrace(t.getResult().getTrace()),
                                    t.getResult().getTime()
                            )
                    )
                    .collect(Collectors.toList());
            return new TestSuitResult(results);

        } catch (TestRetriever.NoSuchATestSuit e) {
            return none;
        }
    }

    private String message(TestResult r) {
        return r.getMessage() != null ? r.getMessage() : "";
    }

    private List<String> stackTrace(StackTraceElement[] elements) {
        return Arrays.stream(elements)
                .map(e -> e.toString())
                .collect(Collectors.toList());
    }

}
