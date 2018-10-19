package com.hm707.loadres;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;
import java.util.jar.JarEntry;

import com.hm707.scanclass.ClassScannerUtils;

public class AccessJarFile {
  public static void main(String[] args) {
    String path = "junit-4.12.jar";
    //access(path);
    Set<Class<?>> classes =  ClassScannerUtils.searchClasses(path);
    System.out.println(classes);

  }

  private static void access(String filePath) {
    try {
      Enumeration<URL> urlEnumeration = Thread.currentThread().getContextClassLoader().getResources(filePath);
      while (urlEnumeration.hasMoreElements()) {
        URL url = urlEnumeration.nextElement();
        if (!url.getProtocol().equals("jar")) {
          //System.err.println("必须是jar文件");
          //System.exit(0);
          continue;
        }
        JarURLConnection jarURLConnection = (JarURLConnection)url.openConnection();
        JarEntry jarEntry = jarURLConnection.getJarEntry();
        System.out.println(jarEntry.getName());
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
