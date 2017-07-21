package org.hulan.config;

import org.hulan.mvc.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：classmetadata实现
 * 时间：2017/7/13 17:42
 * @author ：zhaokuiqiang
 */
public class StandardAnnotationClassMetadata extends StandardClassMetadata{
	
	private Annotation [] annotations;
	
	public StandardAnnotationClassMetadata(Class<?> type) {
		super(type);
		this.annotations = type.getAnnotations();
	}
	
	/**
	 * 返回注解类型名
	 * @return
	 */
	public String[] getAnnotationTypes(){
		String [] types = new String[annotations.length];
		for(int i = 0; i < annotations.length; i++) {
			types[i] = annotations[i].annotationType().getName();
		}
		return types;
	}
	
	public <T> T getAnnotation(Class<T> anno){
		for(Annotation annotation : annotations) {
			if(annotation.annotationType() == anno){
				return (T) annotation;
			}
		}
		return null;
	}
	
	/**
	 * 判断是否包含指定注解
	 * @param annotationName
	 * @return
	 */
	public boolean hasAnnotation(String annotationName){
		for(Annotation annotation : this.annotations) {
			if(annotation.annotationType().getName().equals(annotationName)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 是否存在指定注解的方法
	 * @param annotationName
	 * @return
	 */
	boolean hasAnnotatedMethods(String annotationName){
		Method methods [] = this.type.getDeclaredMethods();
		for(Method method : methods) {
			if(!method.isBridge() && method.getAnnotations().length > 0){
				Annotation [] ann = method.getAnnotations();
				for(int i = 0; i < ann.length; i++) {
					if(ann[i].annotationType().getName().equals(annotationName)){
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 获取指定注解的所有方法
	 * @param annType
	 * @return
	 */
	public List<Method> getAnnotatedMethods(Class<?> annType){
		List<Method> methodList = new ArrayList<>();
		Method methods [] = getMethods();
		for(Method method : methods) {
			if(!method.isBridge() && method.getAnnotations().length > 0){
				Annotation [] ann = method.getAnnotations();
				for(int i = 0; i < ann.length; i++) {
					if(ann[i].annotationType() == annType){
						methodList.add(method);
					}
				}
			}
		}
		return methodList;
	}

}
