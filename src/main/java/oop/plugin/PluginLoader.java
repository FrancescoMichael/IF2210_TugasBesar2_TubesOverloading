package oop.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import oop.saveload.SaveLoad;

public class PluginLoader {

    private ArrayList<String> getClassNamesFromJar(JarInputStream jarFile) throws IOException {
        ArrayList<String> classNames = new ArrayList<>();
        try {
            JarEntry jar;
            while ((jar = jarFile.getNextJarEntry()) != null) {
                if (jar.getName().endsWith(".class") && !jar.getName().contains("META-INF/versions/")) {
                    String className = jar.getName().replace("/", ".").replace(".class", "");
                    classNames.add(className);
                }
            }
        } finally {
            jarFile.close();
        }
        return classNames;
    }

    private ArrayList<String> getClassNamesFromJar(String jarPath) throws IOException {
        try (JarInputStream jarFile = new JarInputStream(new FileInputStream(jarPath))) {
            return getClassNamesFromJar(jarFile);
        }
    }

    public void loadPlugin(String pluginPath, SaveLoad saveLoad) throws Exception {
        try {
            URL jarUrl = new File(pluginPath).toURI().toURL();
            try (URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl}, SaveLoad.class.getClassLoader())) {
                ArrayList<Class<?>> classes = loadJarFile(pluginPath, classLoader);
                System.out.println("Classes found in JAR: " + classes);

                for (Class<?> c : classes) {
                    try {
                        System.out.println("Checking class: " + c.getName());
                        for (Class<?> iface : c.getInterfaces()) {
                            System.out.println("Implements interface: " + iface.getName());
                        }

                        boolean implementsPluginInterface = false;
                        for (Class<?> iface : c.getInterfaces()) {
                            if (iface.getName().equals("PluginInterface")) { // Ensure correct package name
                                implementsPluginInterface = true;
                                break;
                            }
                        }

                        if (implementsPluginInterface) {
                            System.out.println("Before constructor");
                            Constructor<?> constructor = c.getDeclaredConstructor();
                            System.out.println(constructor);
                            PluginInterface pluginInstance = (PluginInterface) constructor.newInstance();
                            System.out.println("Before adding");
                            saveLoad.addSaveLoader(pluginInstance);
                        } else {
                            System.out.println("The class " + c.getName() + " does not implement an interface 'PluginInterface'.");
                        }
                        
                        System.out.println("Finished");
                    } catch (NoSuchMethodException e) {
                        System.out.println("Required method not found in class " + c.getName() + ": " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error invoking methods in class " + c.getName() + ": " + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading plugin: " + e.getMessage());
        }
    }

    private ArrayList<Class<?>> loadJarFile(String filePath, URLClassLoader classLoader) throws Exception {
        ArrayList<Class<?>> availableClasses = new ArrayList<>();
        ArrayList<String> classNames = getClassNamesFromJar(filePath);

        for (String className : classNames) {
            try {
                Class<?> clazz = classLoader.loadClass(className);
                availableClasses.add(clazz);
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found: " + className);
            }
        }
        return availableClasses;
    }
}