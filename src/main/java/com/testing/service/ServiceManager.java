package com.testing.service;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import test.smoketest.utils.*;

import java.io.File;
import java.net.URI;
import java.net.URL;

@SuppressWarnings("all")
public class ServiceManager {
    public static final String BASE_URI = "http://localhost:8080/testing/";

    public static HttpServer start(Class component) throws Exception {

        copyDocXResources();

        new ProduceChromeDriver(new DeterminateExtensionResource(new ExecutableResource(new SingleFileByPath())))
                .create("chromedriver", new File("chromedriver.exe"));

        final ResourceConfig rc = new ResourceConfig().register(component);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static HttpServer start(String packagePath) throws Exception {

        copyDocXResources();

        new ProduceChromeDriver(new DeterminateExtensionResource(new ExecutableResource(new SingleFileByPath())))
                .create("chromedriver", new File("chromedriver.exe"));

        final ResourceConfig rc = new ResourceConfig().packages(packagePath);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

    }

    public static void copyDocXResources() throws Exception {
        FileFromResource copier = new SingleFileByPath();
        File outDirectory = new File("target/classes/resources");
        if (!outDirectory.exists()) {
            outDirectory.mkdirs();
        }
        for (String resource : ClassesFromPackage.getResourcesByExtent("resources",
                s -> s.endsWith("docx")
                        || s.endsWith(".doc")
                        || s.endsWith(".odt")
                        || s.endsWith(".txt")
                        || s.endsWith(".rtf")
        )) {
            String flatName = resource.split("resources")[1].substring(1);
            File file = new File("target/classes/resources/" + flatName);
            file.deleteOnExit();
            if (!file.exists()) {
                copier.create(resource, file);
            }
        }
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

        final HttpServer server = start("com.testing.service");
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.shutdown();
    }
}

