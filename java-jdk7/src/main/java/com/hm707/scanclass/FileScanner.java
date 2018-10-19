package com.hm707.scanclass;


import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Created with LXD
 *
 * @author:Luhui
 * @qq:729913162
 * @Date:2018-08-10
 * @Time:14:51
 */
public class FileScanner implements Scan {

    private String defaultClassPath = FileScanner.class.getResource("/").getPath();

    public String getDefaultClassPath() {
        return defaultClassPath;
    }

    public void setDefaultClassPath(String defaultClassPath) {
        this.defaultClassPath = defaultClassPath;
    }

    public FileScanner(String defaultClassPath) {
        this.defaultClassPath = defaultClassPath;
    }

    public FileScanner(){}

    private static class ClassSearcher{
        private Set<Class<?>> classPaths = new HashSet<>();

        private Set<Class<?>> doPath(File file,String packageName, Predicate<Class<?>> predicate,boolean flag) {

            if (file.isDirectory()) {
                //�ļ������Ǿ͵ݹ�
                File[] files = file.listFiles();
                if(!flag){
                    packageName = packageName+"."+file.getName();
                }

                for (File f1 : files) {
                    doPath(f1,packageName,predicate,false);
                }
            } else {
                if (file.getName().endsWith(CLASS_SUFFIX)) {
                    try {
                        Class<?> clazz = Class.forName(packageName + "."+ file.getName().substring(0,file.getName().lastIndexOf(".")));
                        if(predicate==null||predicate.test(clazz)){
                            classPaths.add(clazz);
                        }
                    } catch (ClassNotFoundException e) {
                        throw new ScannerClassException(e.getMessage(),e);
                    }
                }
            }
            return classPaths;
        }
    }

    @Override
    public Set<Class<?>> search(String packageName, Predicate<Class<?>> predicate) {
        //�ȰѰ���ת��Ϊ·��,���ȵõ���Ŀ��classpath
        String classpath = defaultClassPath;
        //Ȼ������ǵİ���basPackת��Ϊ·����
        String basePackPath = packageName.replace(".", File.separator);
        String searchPath = classpath + basePackPath;
        return new ClassSearcher().doPath(new File(searchPath),packageName, predicate,true);
    }
}
