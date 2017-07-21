package org.hulan.bean;

import lombok.NonNull;
import org.hulan.config.BeanDefinition;
import org.hulan.util.Assert;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述：
 * 时间：2017/7/14 9:56
 * @author ：zhaokuiqiang
 */
public class DefaultBeanFactory implements BeanFactory {
	
	final Map<String,BeanDefinition> beanPool = new ConcurrentHashMap<>();
	String BEAN_PREFIX = "&";
	
	@Override
	public void addBean(Object bean) {
		addBean(bean,true);
	}
	
	@Override
	public void addBean(Object bean,boolean singleton) {
		addBean(bean.getClass().getName(),bean,singleton);
	}
	
	@Override
	public void addBean(Class<?> type) {
		addBean(type,true);
	}
	
	@Override
	public void addBean(Class<?> type,boolean singleton) {
		addBean(type.getName(),type,singleton);
	}
	
	@Override
	public void addBean(String name, Object bean,boolean singleton) {
		BeanDefinition beanDefinition = new BeanDefinition(bean,singleton);
		addBeanDefinition(name,beanDefinition);
	}
	
	@Override
	public void addBean(String name, Class<?> type,boolean singleton) {
		BeanDefinition beanDefinition = new BeanDefinition(type,singleton);
		addBeanDefinition(name,beanDefinition);
	}
	
	@Override
	public <T> T getBean(Class<T> type) {
		BeanDefinition beanDefinition = getBeanDefinition(type);
		if(beanDefinition != null){
			return type.cast(beanDefinition.getBean());
		}
		return null;
	}
	
	@Override
	public <T> T getBean(String name, Class<T> type) {
		BeanDefinition definition = getBeanDefinition(transformBeanPoolKey(name));
		if(definition != null){
			if(type != null){
				if(definition.getClassType() == type){
					return type.cast(definition.getBean());
				}
			}else{
				return (T) definition.getBean();
			}
		}
		return null;
	}
	
	@Override
	public Object getBean(String name) {
		return getBean(name,null);
	}
	
	@Override
	public BeanDefinition addBeanDefinition(BeanDefinition beanDefinition) {
		return addBeanDefinition(beanDefinition.getClassName(),beanDefinition);
	}
	
	@Override
	public BeanDefinition addBeanDefinition(@NonNull String name,@NonNull BeanDefinition beanDefinition) {
		Assert.hasLength(name);
		if(beanPool.get(name) != null){
			throw new RuntimeException("存在相同bean");
		}
		beanPool.put(name,beanDefinition);
		beanPool.put(transformBeanPoolKey(transformClassName(beanDefinition.getClassType())),beanDefinition);
		/*for(Class aClass : beanDefinition.getInterfaces()) {
			BeanDefinition itfDefinition = beanPool.get(aClass.getName());
			Map<String,BeanDefinition> itfMap = null;
			Set<BeanDefinition> definitionSet;
			if(itfDefinition == null){
				definitionSet = new LinkedHashSet<>();
				itfDefinition = new BeanDefinition(aClass,definitionSet,true);
			}else{
				definitionSet = (Set<BeanDefinition>) itfDefinition.getBean();
			}
			definitionSet.add(beanDefinition);
			beanPool.put(aClass.getName(),itfDefinition);
		}*/
		return beanDefinition;
	}
	
	@Override
	public BeanDefinition getBeanDefinition(String name) {
		Assert.hasLength(name);
		BeanDefinition beanDefinition;
		beanDefinition = beanPool.get(name);
		return beanDefinition;
	}
	
	@Override
	public BeanDefinition getBeanDefinition(@NonNull Class<?> type) {
		BeanDefinition beanDefinition;
		beanDefinition = beanPool.get(type.getName());
		return beanDefinition;
	}
	
	@Override
	public boolean containsBean(String name) {
		return beanPool.containsKey(name);
	}
	
	/**
	 * 转换类名为别名(首字母小写)
	 * @param type
	 * @return
	 */
	public String transformClassName(Class<?> type){
		String simpleName = type.getSimpleName();
		return Character.toLowerCase(simpleName.charAt(0))+simpleName.substring(1,simpleName.length());
	}
	
	/**
	 * 转换为可识别的key
	 * @param name
	 * @return
	 */
	public String transformBeanPoolKey(String name){
		return BEAN_PREFIX + name;
	}
}
