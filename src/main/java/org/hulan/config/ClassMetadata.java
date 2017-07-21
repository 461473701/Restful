package org.hulan.config;

import java.lang.reflect.Method;

/**
 * 功能描述：class属性
 * 时间：2017/7/13 17:41
 * @author ：zhaokuiqiang
 */
public interface ClassMetadata {

	String getClassName();
	Class<?> getClassType();
	
	Method [] getMethods();

	boolean isInterface();

	boolean isAnnotation();

	boolean isAbstract();

	boolean isConcrete();

	boolean isFinal();
	
	/**
	 * 是否存在super类
	 * @return
	 */
	boolean hasSuperClass();
	
	/**
	 * 返回super类名
	 * @return
	 */
	String getSuperClassName();
	
	/**
	 * 返回super类
	 * @return
	 */
	Class<?> getSuperClass();
	
	/**
	 * 得到所有接口
	 * @return
	 */
	String[] getInterfaceNames();
	
	/**
	 * 获取所有接口
	 * @return
	 */
	Class[] getInterfaces();
}
