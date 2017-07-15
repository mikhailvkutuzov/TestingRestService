package com.testing.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.net.URL;

import static com.testing.service.TestingService.BASE_URI;

/**
 * This class intended to make an access for a {@link test.smoketest.test.TestSuite}
 * through the REST web service by their names.
 */
@SuppressWarnings("all")
public class ServiceManager {

    private static TestingService service;

    public static TestingService getService() {
        return service;
    }

    /**
     * This service starts if and only if [urlToBeTested] is handed on as an argument.
     * @param args urlToBeTested=http://<something>
     * @throws Exception if no valid arguments are passed
     */
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            throw new IllegalAccessException("Only one argument should be present");
        }
        String[] urlToBeTested = args[0].split("=");
        if (urlToBeTested.length != 2) {
            throw new IllegalAccessException();
        } else {
            if (!urlToBeTested[0].equals("urlToBeTested")) {
                throw new IllegalAccessException("An argument name is not ");
            }
        }
        new URL(urlToBeTested[1]);
        System.setProperty("urlToBeTested",  urlToBeTested[1]);

        Client c = ClientBuilder.newClient();
        WebTarget target = c.target(String.format(BASE_URI, 8181));
        try {
            target.path("administration/stop").request().get();
        } catch (Exception  e){
        }

        service = new CopyDocumentsFromClassPath(new ChromeTestingService(new GrizzlyTestingService()));

        service.start("com.testing.service");

        System.out.println(String.format("Jersey app started with WADL available at %sapplication.wadl", String.format(BASE_URI, 8181)));
    }
}

