package org.hulan.util;

import lombok.NonNull;
import org.hulan.config.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 功能描述：
 * 时间：2017/7/15 14:15
 * @author ：zhaokuiqiang
 */
public class ScanUtils {
	
	/**
	 * 获取指定包下所有文件
	 * @param basePackage
	 * @return
	 */
	public static Set<BeanDefinition> scan(String basePackage){
		try {
			Set<URL> urlSet = findAllResource(basePackage);
			Set<BeanDefinition> definitions = new LinkedHashSet<>();
			urlSet.forEach(url -> {
				File file = new File(url.getFile());
				try {
					doScan("*.class",basePackage,file,definitions);
				} catch(ClassNotFoundException e) {
					throw new RuntimeException(e);
				}
			});
			return definitions;
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取文件目录下所有class文件
	 * @param fullPattern
	 * @param dir
	 * @param result
	 */
	public static void doScan(String fullPattern,String basePackage,File dir, Set<BeanDefinition> result) throws ClassNotFoundException {
		File[] dirContents = dir.listFiles();
		Arrays.sort(dirContents);
		for(File content : dirContents) {
			String currPath = StringUtils.replace(content.getAbsolutePath(), File.separator, "/");
			if(content.isDirectory()) {
				if(!content.canRead()) {
				} else {
					String newPackage = basePackage + "." + content.getName();
					doScan(fullPattern,newPackage,content, result);
				}
			}else{
				if(PathMatcherUtils.match(fullPattern, currPath)) {
					String beanName = basePackage + "." + content.getName().substring(0,content.getName().length() - 6);
					BeanDefinition definition = new BeanDefinition(Thread.currentThread().getContextClassLoader().loadClass(beanName));
					if(definition.isConcrete()){
						result.add(definition);
					}
				}
			}
		}
	}
	
	/**
	 * 根据package获取resource
	 * @param basePackage
	 * @return
	 */
	public static Set<URL> findAllResource(String basePackage) throws IOException {
		Set<URL> result = new LinkedHashSet<>();
		ClassLoader loader = ScanUtils.class.getClassLoader();
		Enumeration<URL> enumeration = (loader != null ? loader.getResources(basePackage.replace(".","/"))
				: ClassLoader.getSystemResources(basePackage));
		while(enumeration.hasMoreElements()){
			URL url = enumeration.nextElement();
			result.add(url);
		}
		return result;
	}
	
	/**
	 * 转换package为resource
	 * @param className
	 * @return
	 */
	public static String convertClassNameToResourcePath(@NonNull String className) {
		return className.replace(".", "/");
	}
	
}
