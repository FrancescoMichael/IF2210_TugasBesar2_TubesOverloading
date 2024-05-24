

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
            try (URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl}, getClass().getClassLoader())) {
                ArrayList<Class<?>> classes = loadJarFile(pluginPath, classLoader);

                for (Class<?> c : classes) {
                    try {
                        boolean implementsPluginInterface = false;
                        for (Class<?> iface : c.getInterfaces()) {
                            if (iface.getName().equals("oop.plugin.PluginInterface")) {
                                implementsPluginInterface = true;
                                break;
                            }
                        }

                        if (implementsPluginInterface) {
                            Constructor<?> constructor = c.getDeclaredConstructor();
                            PluginInterface pluginInstance = (PluginInterface) constructor.newInstance();
                            saveLoad.addSaveLoader(pluginInstance);
                            System.out.println("Plugin Success!!!");
                        } else {
                            // System.out.println("The class " + c.getName() + " does not implement an interface 'PluginInterface'.");
                        }
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