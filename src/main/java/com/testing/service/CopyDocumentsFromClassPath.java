package com.testing.service;

import org.glassfish.grizzly.http.server.HttpServer;
import test.smoketest.utils.FileFromResource;
import test.smoketest.utils.SingleFileByPath;

import java.io.File;

/**
 * This REST service intended to get documents from the class path by specific extensions
 * and put them into target/classes/resources to be read by WebDriver {@link test.smoketest.test.TestCase}s
 * working with file system objects(namely documents).
 *
 * Created by mikhail.kutuzov on 10.07.17.
 */
public class CopyDocumentsFromClassPath extends DelegatingService {

    public CopyDocumentsFromClassPath(TestingService service) {
        super(service);
    }

    @Override
    public void start(Class component) throws Exception {
        copyDocXResources();
        getService().start(component);
    }

    @Override
    public void start(String packagePath) throws Exception {
        copyDocXResources();
        getService().start(packagePath);
    }

    private static class CouldNotCreateDocumentsResourceFolder extends RuntimeException {}

    private static void copyDocXResources() throws Exception {
        FileFromResource copier = new SingleFileByPath();
        File outDirectory = new File("target/classes/resources");
        if (!outDirectory.exists()) {
            if(!outDirectory.mkdirs()) {
                throw new CouldNotCreateDocumentsResourceFolder();
            }
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

}
