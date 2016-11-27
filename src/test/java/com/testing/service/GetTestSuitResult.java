package com.testing.service;


import com.testing.service.entities.TestCaseDescription;
import com.testing.service.entities.TestCaseResult;
import com.testing.service.entities.TestSuitResult;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/test-suit-result")
public class GetTestSuitResult {

    @GET
    @Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public TestSuitResult getXmlTestSuitResult() {
        return result();
    }

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public TestSuitResult getJsonTestSuitResult() {
        return result();
    }


    private TestSuitResult result (){
        TestSuitResult result = new TestSuitResult();
        List<TestCaseResult> results = new ArrayList<>(1);
        result.setResults(results);
        TestCaseResult caseResult = new TestCaseResult();
        caseResult.setDescription(new TestCaseDescription("TestCase", "Description"));
        caseResult.setMessage("message");
        results.add(caseResult);

        return result;
    }


}