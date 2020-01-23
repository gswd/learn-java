package com.hm707.scanclass;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created with LXD
 *jarɨ����
 * @author:Luhui
 * @qq:729913162
 * @Date:2018-08-10
 * @Time:15:36
 */
public class JarScanner implements Scan {

    @Override
    public Set<Class<?>> search(String packageName, Predicate<Class<?>> predicate) {

        Set<Class<?>> classes = new HashSet<>();

        try {
            Enumeration<URL> urlEnumeration = Thread.currentThread().getContextClassLoader().getResources(packageName.replace(".", "/"));
            while (urlEnumeration.hasMoreElements()) {
                URL url = urlEnumeration.nextElement();
                String protocol = url.getProtocol();
                if ("jar".equalsIgnoreCase(protocol)) {

                    JarURLConnection connection = (JarURLConnection) url.openConnection();
                    if (connection != null) {
                        JarFile jarFile = connection.getJarFile();
                        if (jarFile != null) {
                            Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
                            while (jarEntryEnumeration.hasMoreElements()) {
                                JarEntry entry = jarEntryEnumeration.nextElement();
                                String jarEntryName = entry.getName();
                                if (jarEntryName.contains(".class") && jarEntryName.replaceAll("/", ".").startsWith(packageName)) {
                                    String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replace("/", ".");
                                    Class cls = Class.forName(className);
                                    if(predicate == null || predicate.test(cls)){
                                        classes.add(cls);
                                    }
                                }
                            }
                        }
                    }
                }else if("file".equalsIgnoreCase(protocol)){
                    FileScanner fileScanner = new FileScanner();
                    fileScanner.setDefaultClassPath(url.getPath().replace(packageName.replace(".", "/"),""));
                    classes.addAll(fileScanner.search(packageName,predicate));
                }
            }
        }catch (ClassNotFoundException | IOException e){
            throw new ScannerClassException(e.getMessage(),e);
        }
        return classes;
    }
}
