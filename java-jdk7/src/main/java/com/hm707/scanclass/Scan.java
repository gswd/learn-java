package com.hm707.scanclass;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Created with LXD
 *
 * @author:Luhui
 * @qq:729913162
 * @Date:2018-08-10
 * @Time:14:46
 */
public interface Scan {

    String CLASS_SUFFIX = ".class";

    Set<Class<?>> search(String packageName, Predicate<Class<?>> predicate);

    default Set<Class<?>> search(String packageName){
        return search(packageName,null);
    }

}
