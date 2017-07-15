package com.testing.service;

/**
 * Testing service delegate to composite a real service.
 *
 * Created by mikhail.kutuzov on 10.07.17.
 */
public class DelegatingService implements TestingService {
    private TestingService service;

    protected TestingService getService() {
        return service;
    }

    public DelegatingService(TestingService service) {
        this.service = service;
    }

    @Override
    public void start(Class component) throws Exception {
        service.start(component);
    }

    @Override
    public void start(String packagePath) throws Exception {
        service.start(packagePath);
    }

    @Override
    public void stop() {
        service.stop();
    }
}
