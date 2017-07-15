package com.testing.service;

import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static com.testing.service.TestingService.BASE_URI;

/**
 * To check for administration operations.
 *
 * Created by mikhail kutuzov on 15.07.2017.
 */
public class TestAdministration {

    @Test
    public void test() throws Exception {

        ServiceManager.main(new String[]{"urlToBeTested=http://google.com/"});

        Client c = ClientBuilder.newClient();
        WebTarget target = c.target(String.format(BASE_URI, 8181));
        try {
            target.path("administration/stop").request().get();
        } catch (Exception  e){
        }

        ServiceManager.main(new String[]{"urlToBeTested=http://google.com/"});
    }

}
