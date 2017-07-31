package com.testing.service;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

/**
 * This server intended to create through it's methods a simple Grizzly service.
 * Created by mikhail.kutuzov on 10.07.17.
 */
public class GrizzlyTestingService implements TestingService {
    private HttpServer server;
    private int port;

    public GrizzlyTestingService(int port) {
        this.port = port;
    }

    public void start(Class component) throws Exception {
        final ResourceConfig rc = new ResourceConfig().register(component);
        server = GrizzlyHttpServerFactory.createHttpServer(URI.create(String.format(BASE_URI, port)), rc);
    }

    public void start(String packagePath) throws Exception {
        final ResourceConfig rc = new ResourceConfig().packages(packagePath);
        server = GrizzlyHttpServerFactory.createHttpServer(URI.create(String.format(BASE_URI, port)), rc);
    }

    @Override
    public void stop() {
        server.shutdownNow();
    }
}
