package com.testing.service;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/data-test")
public class GotItService {
    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam(value = "id") String id) {
        return "Got it!" + id;
    }
}