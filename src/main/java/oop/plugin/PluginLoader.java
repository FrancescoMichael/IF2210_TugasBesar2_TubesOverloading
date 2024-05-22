// File: src/main/java/main/Plugin/PluginLoader.java
package oop.plugin;

import oop.plugin.PluginInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class PluginLoader {
    // Returns an array list of class names in a JarInputStream
    private ArrayList<String> getClassNamesFromJar(JarInputStream jarFile) throws Exception {
        ArrayList<String> classNames = new ArrayList<>();
        try {
            JarEntry jar;
            // Iterate through the contents of the jar file
            while (true) {
                jar = jarFile.getNextJarEntry();
                if (jar == null) {
                    break;
                }
                // Pick file that has the extension of .class
                if (jar.getName().endsWith(".class")) {
                    String className = jar.getName().replaceAll("/", "\\.");
                    String myClass = className.substring(0, className.lastIndexOf('.'));
                    classNames.add(myClass);
                }
            }
        } catch (Exception e) {
            throw new Exception("Error while getting class names from jar", e);
        }
        return classNames;
    }

    // Returns an array list of class names in a JarInputStream
    // Calls the above function by converting the jar path to a stream
    private ArrayList<String> getClassNamesFromJar(String jarPath) throws Exception {
        return getClassNamesFromJar(new JarInputStream(new FileInputStream(jarPath)));
    }

    // Get an array list of all the loaded classes in a jar file
    private ArrayList<Class<?>> loadJarFile(String filePath) throws Exception {
        ArrayList<Class<?>> availableClasses = new ArrayList<>();

        ArrayList<String> classNames = getClassNamesFromJar(filePath);
        File f = new File(filePath);

        URLClassLoader classLoader = new URLClassLoader(new URL[] { f.toURI().toURL() });
        for (String className : classNames) {
            try {
                Class<?> cc = classLoader.loadClass(className);
                final List<Class<?>> implementedInterfaces = Arrays.asList(cc.getInterfaces());
                for (Class<?> inter : implementedInterfaces) {
                    if (inter.getName().equals("oop.plugin.PluginInterface")) {
                        availableClasses.add(cc);
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                continue;
            }
        }
        return availableClasses;
    }

    public void loadPlugin(String pluginPath) throws Exception {
        try {
            ArrayList<Class<?>> classes = loadJarFile(pluginPath);
            for (Class<?> c : classes) {
                try {
                    Method loadMethod = c.getMethod("onLoad", String.class);
                    Method saveMethod = c.getMethod("onSave", String.class);

                    if (loadMethod != null && saveMethod != null) {
                        Constructor<?> constructor = c.getDeclaredConstructor();
                        PluginInterface plugin = (PluginInterface) constructor.newInstance();

                    } else {
                        System.out.println("The plugin does not have the required methods.");
                    }
                } catch (Exception e) {
                    System.out.println("Error invoking methods: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading plugin: " + e.getMessage());
        }
    }
}
