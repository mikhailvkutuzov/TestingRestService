package com.testing.service;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import test.smoketest.utils.ProduceChromeDriver;
import test.smoketest.utils.SingleFileByPath;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class ServiceManager {
    public static final String BASE_URI = "http://localhost:8080/testing/";

    public static HttpServer start(Class component) {

        new ProduceChromeDriver(new SingleFileByPath()).create("chromedriver.exe", new File("chromedriver.exe"));

        final ResourceConfig rc = new ResourceConfig().register(component);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = start(TestCaseResources.class);
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}

