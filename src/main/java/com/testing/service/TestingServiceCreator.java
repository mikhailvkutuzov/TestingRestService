package com.testing.service;

import org.glassfish.grizzly.http.server.HttpServer;

/**
 * A way create Grizzly servers.
 * Created by mikhail.kutuzov on 10.07.17.
 */
public interface TestingServiceCreator {
    String BASE_URI = "http://localhost:%d/testing/";

    HttpServer start(Class component) throws Exception;

    HttpServer start(String packagePath) throws Exception;
}
