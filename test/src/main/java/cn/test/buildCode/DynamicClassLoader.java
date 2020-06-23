package cn.test.buildCode;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Stack;

/**
 * DynamicClassLoader 动态类加载器
 *
 */
public class DynamicClassLoader extends ClassLoader {

    private String classPath;

    public DynamicClassLoader(String classPath, ClassLoader parent) {
        super(parent);
        this.classPath = classPath;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        // 判断当前加载的类是否是需要动态重新加载的类，
        // 假如是通过重写的findClass在自定义的ClassLoader里面加载，
        // 假如不是就调用父ClassLoader默认加载
        if (name != null && name.equals(classPath)) {
            return findClass(name);
        }
        return super.loadClass(name, false);
    }

    /**
     * 根据类名查找class
     *
     * @param fullClassPath 类全路径（包）
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String fullClassPath)
            throws ClassNotFoundException {
        byte[] raw = readClassBytes(fullClassPath);
        // definClass方法参数说明：name：类包的全路径如com.lkb.sb.client.shanghaiC.ShangHaiLoginClient
        //                         b：读取的class文件的byte数组
        //                         off：从byte数组中读取的索引
        //                         len：从byte数组中读取的长度
        // 注：假如此类中有引入别的class类，如com.lkb.sb.client.BaseClient，循环执行findClass方法
        Class<?> clazz = defineClass(fullClassPath, raw, 0, raw.length);
        // 连接class
        resolveClass(clazz);
        return clazz;
    }

    /**
     * 读取class
     *
     * @param fullClassPath
     * @return
     */
    private byte[] readClassBytes(String fullClassPath) {
        byte[] raw = null;
        InputStream stream = null;
        try {
            File file = new File("E:/workspace/shequ/fan-web-shequyy/target/classes/" + fullClassPath.replaceAll("\\.", "/")+".class");
            stream = new FileInputStream(file);
            raw = new byte[(int) file.length()];
            stream.read(raw);
        } catch (Exception e) {

        } finally {
            try {
                stream.close();
            } catch (Exception e) {
            }
        }
        return raw;
    }

    
public static String getClassFile(String loadPath) throws Exception {
	File clazzPath = new File(loadPath);
	 
	// 记录加载.class文件的数量
	int clazzCount = 0;
	 String clazz = null;
	if (clazzPath.exists() && clazzPath.isDirectory()) {
	    // 获取路径长度
	    int clazzPathLen = clazzPath.getAbsolutePath().length() + 1;
	 
	    Stack<File> stack = new Stack<File>();
	    stack.push(clazzPath);
	 
	    // 遍历类路径
	    while (stack.isEmpty() == false) {
	        File path = stack.pop();
	        File[] classFiles = path.listFiles(new FileFilter() {
	            public boolean accept(File pathname) {
	                return pathname.isDirectory() || pathname.getName().endsWith(".class");
	            }
	        });
	        for (File subFile : classFiles) {
	            if (subFile.isDirectory()) {
	                stack.push(subFile);
	            } else {
	                if (clazzCount++ == 0) {
	                    Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
	                    boolean accessible = method.isAccessible();
	                    try {
	                        if (accessible == false) {
	                            method.setAccessible(true);
	                        }
	                        // 设置类加载器
	                        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
	                        // 将当前类路径加入到类加载器中
	                        method.invoke(classLoader, clazzPath.toURI().toURL());
	                    } finally {
	                        method.setAccessible(accessible);
	                    }
	                }
	                // 文件名称
	                String className = subFile.getAbsolutePath();
	                className = className.substring(clazzPathLen, className.length() - 6);
	                className = className.replace(File.separatorChar, '.');
	                // 加载Class类
	                Class.forName(className);
	                System.out.println("读取应用程序类文件[class={}]"+ className);
	                clazz = className;
	            }
	        }
	    }
	}
	return clazz;	
}
}