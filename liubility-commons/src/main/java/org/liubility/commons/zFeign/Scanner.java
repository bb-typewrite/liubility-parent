package org.liubility.commons.zFeign;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Author: Jdragon
 * @email: 1061917196@qq.com
 * @Date: 2020.11.22 23:53
 * @Description:
 */
@Slf4j
public class Scanner {

    /**
     * 扫描文件后缀
     */
    private static final String SUFFIX_CLASS = ".class";

    private static final String RUN_JAR = "jar";

    private static final String RUN_FILE = "file";


    /**
     * 文件路径间隔
     */
    private final String PATH_SEPARATOR = "/";
    /**
     * 类包路径间隔
     */
    private final String PKG_SEPARATOR = ".";

    /**
     * 扫描根路径
     */
    private String scanBasePackage;
    /**
     * 放置bean
     */
    private final ClassLoader classLoader;

    private final String[] baseClassesName;

    private ScanMatch scanMatch = new DefaultMatch();

    private final List<Class<?>> isMatchClass = new LinkedList<>();

    public Scanner(String... baseClassesName) {
        this.baseClassesName = baseClassesName;
        String SCAN_BASE_PACKAGE;

        this.classLoader = getClass().getClassLoader();

        try {
            SCAN_BASE_PACKAGE = Objects.requireNonNull(classLoader.getResource("")).getPath();
        } catch (Exception ignored) {
            SCAN_BASE_PACKAGE = "";
        }

        this.scanBasePackage = SCAN_BASE_PACKAGE;
    }

    public void setScanMatch(ScanMatch scanMatch) {
        this.scanMatch = scanMatch;
    }

    public List<Class<?>> doScan() {
        isMatchClass.clear();
        for (String baseClazzName : baseClassesName) {
            String baseClazzPackage = baseClazzName;
            //filter未初始化前扫类，后扫包
            baseClazzPackage = baseClazzPackage.replaceAll("\\" + PKG_SEPARATOR, PATH_SEPARATOR);
            URL resource = classLoader.getResource(baseClazzPackage);
            String runType = Objects.requireNonNull(resource).getProtocol();
            log.info("扫描[{}]", baseClazzPackage);

            //更改扫描基路径，因为可能跨越不同的jar包，所获取的资源路径也不一样
            this.scanBasePackage = resource.getPath().replace(baseClazzPackage, "");

            if (RUN_JAR.equals(runType)) {
                this.scanJarPackage(baseClazzPackage);
            } else if (RUN_FILE.equals(runType)) {
                this.scanFilePackage(baseClazzPackage);
            }
        }
        return isMatchClass;
    }

    /**
     * 注意：scanJarPackage与scanPackage的差异：
     * 一、因为打包后不能直接使用File来获取resources资源
     * 需要使用jarFile来获取jarEntry再获取类信息
     * <p>
     * 二、并且要小心对待File与jarEntry的name属性差异
     * 1、File的name属性是文件名，而jarEntry的name属性是文件路径+文件名
     * 2、JarURLConnection.getJarFile().entries自带文件递归效果，不需要递归读取文件
     * <p>
     * 三、使用scanCache缓存第一次扫描的文件夹，之后对需要扫描文件夹的操作直接取cache
     **/
    private void scanJarPackage(final String jarPkg) {
        JarFile jarBaseFilePath;
        try {
            URL url = classLoader.getResource(jarPkg);
            JarURLConnection jarUrlConnection = (JarURLConnection)
                    Objects.requireNonNull(url).openConnection();

            jarBaseFilePath = jarUrlConnection.getJarFile();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Enumeration<JarEntry> jarFileEntries = jarBaseFilePath.entries();
        List<JarEntry> jarClass = new LinkedList<>();

        while (jarFileEntries.hasMoreElements()) {
            JarEntry jarFileEntry = jarFileEntries.nextElement();
            String jarEntryName = jarFileEntry.getName();
            //判断是否不为文件夹，且后缀是否为.class
            if (jarEntryName.contains(jarPkg) && !jarFileEntry.isDirectory() && jarEntryName.endsWith(SUFFIX_CLASS)) {
                jarClass.add(jarFileEntry);
            }
        }

        for (JarEntry clazzFile : jarClass) {
            String clazzFileName = clazzFile.getName();
            // 要先将最后的.class后缀删除，再将文件分隔符'/'替换成包类分隔符'.'
            clazzFileName = clazzFileName
                    .substring(0, clazzFileName.lastIndexOf(PKG_SEPARATOR))
                    .replaceAll(PATH_SEPARATOR, PKG_SEPARATOR);


            //在上方已将文件分隔符替换成包类分隔符，可以作为全类名来获取class对象
            String className = clazzFileName;
            match(className);
        }
    }

    private void scanFilePackage(final String pkg) {
        String absDir = scanBasePackage + pkg;
        File absFile = new File(absDir);
        File[] absSubFiles = Optional.ofNullable(
                absFile.listFiles(file -> {
                    String fName = file.getName();
                    if (file.isDirectory()) {
                        this.scanFilePackage(pkg + PATH_SEPARATOR + fName);
                    } else {
                        //判断文件后缀是否为.class
                        return fName.endsWith(SUFFIX_CLASS);
                    }
                    return false;
                })).orElse(new File[0]);

        String pkgPath = pkg.replaceAll(PATH_SEPARATOR, "\\" + PKG_SEPARATOR);

        for (File clazzFile : absSubFiles) {
            String clazzFileName = clazzFile.getName();
            //去除.class以后的文件名
            clazzFileName = clazzFileName
                    .substring(0, clazzFileName.lastIndexOf(PKG_SEPARATOR));
            //构建一个类全名(包名.类名)
            String className = pkgPath + PKG_SEPARATOR + clazzFileName;
            match(className);
        }
    }

    private void match(String className) {
        try {
            Class<?> aClass = Class.forName(className);
            if (scanMatch.match(aClass)) {
                isMatchClass.add(aClass);
            }
        } catch (ClassNotFoundException e) {
            log.warn("扫描Jar时出现无法创建对象的类[{}]", className);
        }
    }

    static class DefaultMatch implements ScanMatch{
        @Override
        public boolean match(Class<?> clazz) {
            return true;
        }
    }
}
