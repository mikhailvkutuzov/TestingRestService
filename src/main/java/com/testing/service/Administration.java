package com.testing.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * This class intended to execute administration tasks made with http requests.
 * Created by mikhail kutuzov on 12.07.2017.
 */
@Path("/administration")
public class Administration {
    @GET
    @Path("stop")
    @Produces(MediaType.APPLICATION_JSON)
    public void stop(){
        ServiceManager.getService().stop();
    }
}
