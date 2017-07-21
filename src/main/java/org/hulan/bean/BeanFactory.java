package org.hulan.bean;

import org.hulan.config.BeanDefinition;

/**
 * 功能描述：
 * 时间：2017/7/13 17:16
 * @author ：zhaokuiqiang
 */
public interface BeanFactory {
	
	/**
	 * 添加bean
	 * @param bean
	 */
	void addBean(Object bean);
	/**
	 * 添加bean
	 * @param bean
	 */
	void addBean(Object bean,boolean singleton);
	
	/**
	 * 添加bean
	 * @param type
	 */
	void addBean(Class<?> type);
	
	/**
	 * 添加bean
	 * @param type
	 */
	void addBean(Class<?> type,boolean singleton);
	
	/**
	 * 添加bean
	 * @param name
	 * @param bean
	 */
	void addBean(String name, Object bean,boolean singleton);
	/**
	 * 添加bean
	 * @param name
	 * @param type
	 */
	void addBean(String name, Class<?> type,boolean singleton);
	
	/**
	 * 获取bean
	 * @param type
	 * @param <T>
	 * @return
	 */
	<T> T getBean(Class<T> type);
	
	/**
	 * 获取bean
	 * @param name
	 * @param type
	 * @param <T>
	 * @return
	 */
	<T> T getBean(String name,Class<T> type);
	
	/**
	 * 获取bean
	 * @param name
	 * @return
	 */
	Object getBean(String name);
	
	/**
	 * 添加beandefinition
	 * @param beanDefinition
	 * @return
	 */
	BeanDefinition addBeanDefinition(BeanDefinition beanDefinition);
	
	/**
	 * 添加beandefinition
	 * @param beanDefinition
	 * @return
	 */
	BeanDefinition addBeanDefinition(String name,BeanDefinition beanDefinition);
	
	/**
	 * 通过别名获取beandefinition
	 * @param name
	 * @return
	 */
	BeanDefinition getBeanDefinition(String name);
	
	/**
	 * 通过类型获取beandefinition
	 * @param name
	 * @return
	 */
	BeanDefinition getBeanDefinition(Class<?> type);
	
	/**
	 * 是否包含bean
	 * @param name
	 * @return
	 */
	boolean containsBean(String name);
}
