package com.testing.service;

/**
 * A way create Grizzly servers.
 * Created by mikhail.kutuzov on 10.07.17.
 */
public interface TestingService {
    String BASE_URI = "http://localhost:%d/testing/";

    void start(Class component) throws Exception;

    void start(String packagePath) throws Exception;

    void stop();
}
