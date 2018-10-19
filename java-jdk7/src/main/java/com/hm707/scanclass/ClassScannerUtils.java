package com.hm707.scanclass;


import java.util.Set;
import java.util.function.Predicate;

/**
 * Created with LXD
 *
 * @author:Luhui
 * @qq:729913162
 * @Date:2018-08-10
 * @Time:15:41
 */
public class ClassScannerUtils {

    public static Set<Class<?>> searchClasses(String packageName){
        return searchClasses(packageName,null);
    }

    public static Set<Class<?>> searchClasses(String packageName, Predicate predicate){
        return ScanExecutor.getInstance().search(packageName,predicate);
    }
}
