package com.testing.service;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import test.smoketest.utils.FileFromResource;
import test.smoketest.utils.ProduceChromeDriver;
import test.smoketest.utils.SingleFileByPath;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ServiceManager {
    public static final String BASE_URI = "http://localhost:8080/testing/";

    public static HttpServer start(Class component) throws Exception {

        copyDocXResources();

        new ProduceChromeDriver(new SingleFileByPath()).create("chromedriver.exe", new File("chromedriver.exe"));

        final ResourceConfig rc = new ResourceConfig().register(component);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static HttpServer start(String packagePath) throws Exception {

        copyDocXResources();

        new ProduceChromeDriver(new SingleFileByPath()).create("chromedriver.exe", new File("chromedriver.exe"));

        final ResourceConfig rc = new ResourceConfig().packages(packagePath);

        HttpServer server =  GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

        return server;

    }

    public static void copyDocXResources() throws Exception {
        FileFromResource copier = new SingleFileByPath();
        File outDirectory = new File("target/classes/resources");
        if(!outDirectory.exists()) {
            outDirectory.mkdirs();
        }
        for(String resource : ClassesFromPackage.getResourcesByExtent("resources",
                s -> s.endsWith("docx")
                        || s.endsWith(".doc")
                        || s.endsWith(".odt")
                        || s.endsWith(".txt")
        )){
            String flatName = resource.split("resources")[1].substring(1);
            File file = new File("target/classes/resources/"+flatName);
            file.deleteOnExit();
            if(!file.exists()) {
                copier.create(resource, file);
            }
        }
    }


    public static void main(String[] args) throws Exception {
        final HttpServer server = start("com.testing.service");
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}

