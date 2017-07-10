package com.testing.service;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

/**
 * This server intended to create through it's methods a simple Grizzly service.
 * Created by mikhail.kutuzov on 10.07.17.
 */
public class CoreTestingServiceCreator implements TestingServiceCreator {

    public HttpServer start(Class component) throws Exception {

        final ResourceConfig rc = new ResourceConfig().register(component);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(String.format(BASE_URI, 8181)), rc);
    }

    public HttpServer start(String packagePath) throws Exception {
        final ResourceConfig rc = new ResourceConfig().packages(packagePath);

       return GrizzlyHttpServerFactory.createHttpServer(URI.create(String.format(BASE_URI, 8181)), rc);
    }
}
