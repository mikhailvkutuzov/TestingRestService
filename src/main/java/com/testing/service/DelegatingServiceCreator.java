package com.testing.service;

import org.glassfish.grizzly.http.server.HttpServer;

/**
 * Testing service delegate to composite a real Grizzly's service creator.
 *
 * Created by mikhail.kutuzov on 10.07.17.
 */
public class DelegatingServiceCreator implements TestingServiceCreator {
    private TestingServiceCreator service;

    protected TestingServiceCreator getService() {
        return service;
    }

    public DelegatingServiceCreator(TestingServiceCreator service) {
        this.service = service;
    }

    @Override
    public HttpServer start(Class component) throws Exception {
        return service.start(component);
    }

    @Override
    public HttpServer start(String packagePath) throws Exception {
        return service.start(packagePath);
    }
}
