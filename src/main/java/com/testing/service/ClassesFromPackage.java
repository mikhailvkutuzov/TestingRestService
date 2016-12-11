package com.testing.service;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * User: mvkutuzov
 * Date: 24.07.13
 * Time: 11:37
 * <p/>
 * Код, который возвращает набор классов сообщений по имени пакета, реализован благодаря усилиям ресурса
 * http://www.dzone.com/snippets/get-all-classes-within-package
 */
@SuppressWarnings("unchecked")
public class ClassesFromPackage {

    private ClassesFromPackage() {
    }

    public static List<Class> getClasses(String packageName) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List<String> dirs = new ArrayList();
        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            dirs.add(resource.getFile());
        }
        TreeSet<String> classes = new TreeSet();
        for (String directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        ArrayList<Class> classList = new ArrayList();
        for (String clazz : classes) {
            classList.add(Class.forName(clazz));
        }
        return classList;
    }

    public static List<String> getResourcesByExtent(String packageName, Predicate<String> test) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List<String> dirs = new ArrayList();
        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            dirs.add(resource.getFile());
        }
        List<String> mappings = new ArrayList<>();
        for (String directory : dirs) {
            mappings.addAll(findFilesByExtent(directory, packageName, test));
        }
        return mappings;
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirectories.
     * Adapted from http://snippets.dzone.com/posts/show/4831 and extended to support use of JAR files
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static TreeSet<String> findClasses(String directory, String packageName) throws Exception {
        TreeSet<String> classes = new TreeSet<>();
        if (directory.startsWith("file:") && directory.contains("!")) {
            String[] split = directory.split("!");
            URL jar = new URL(split[0]);
            ZipInputStream zip = new ZipInputStream(jar.openStream());
            ZipEntry entry = null;
            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().endsWith(".class")) {
                    String className = entry.getName().replaceAll("[$].*", "").replaceAll("[.]class", "").replace('/', '.');
                    classes.add(className);
                }
            }
        }
        File dir = new File(directory);
        if (!dir.exists()) {
            return classes;
        }
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file.getAbsolutePath(), packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(packageName + '.' + file.getName().substring(0, file.getName().length() - ".class".length()));
            }
        }
        return classes;
    }



    private static TreeSet<String> findFilesByExtent(String directory, String packageName, Predicate<String> test) throws Exception {
        TreeSet<String> hbmClasses = new TreeSet<>();
        if (directory.startsWith("file:") && directory.contains("!")) {
            System.out.println("find in jar");
            String[] split = directory.split("!");
            URL jar = new URL(split[0]);
            ZipInputStream zip = new ZipInputStream(jar.openStream());
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (test.test(entry.getName())) {
                    String className = entry.getName();
                    hbmClasses.add(className);
                }
            }
        }
        File dir = new File(directory);
        if (!dir.exists()) {
            return hbmClasses;
        }
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                hbmClasses.addAll(findFilesByExtent(file.getAbsolutePath(), packageName + "." + file.getName(), test));
            } else if (test.test(file.getName())) {
                hbmClasses.add(packageName + '.' + file.getName());
            }
        }
        return hbmClasses;
    }

}
