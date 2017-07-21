package org.hulan.config;

import org.hulan.util.Assert;

/**
 * 功能描述：
 * 时间：2017/7/13 17:26
 * @author ：zhaokuiqiang
 */
public class BeanDefinition extends StandardAnnotationClassMetadata{
	Object bean;
	boolean singleton = true;
	
	public BeanDefinition(Class<?> type) {
		super(type);
	}
	
	public BeanDefinition(Class<?> type,boolean singleton) {
		super(type);
		this.singleton = singleton;
	}
	
	public BeanDefinition(Object bean) {
		super(bean.getClass());
		this.bean = bean;
	}
	
	public BeanDefinition(Object bean, boolean singleton) {
		super(bean.getClass());
		this.bean = bean;
		this.singleton = singleton;
	}
	
	public BeanDefinition(Class<?> type, Object bean, boolean singleton) {
		super(type);
		this.bean = bean;
		this.singleton = singleton;
	}
	
	public Object getBean() {
		if(bean == null || !isSingleton()){
			synchronized(this){
				if(bean == null || !isSingleton()){
					try {
						return bean = type.newInstance();
					} catch(InstantiationException e) {
						throw new RuntimeException(e);
					} catch(IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
		return bean;
	}
	
	public void setBean(Object bean) {
		this.bean = bean;
	}
	
	public boolean isSingleton() {
		return singleton;
	}
	
	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}
}
