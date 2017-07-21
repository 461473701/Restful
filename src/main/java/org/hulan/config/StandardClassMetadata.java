package org.hulan.config;

import lombok.NonNull;
import org.hulan.util.Assert;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 功能描述：classmetadata实现
 * 时间：2017/7/13 17:42
 * @author ：zhaokuiqiang
 */
public class StandardClassMetadata implements ClassMetadata{
	
	Class<?> type;
	Method [] methods;
	
	public StandardClassMetadata(@NonNull Class<?> type) {
		this.type = type;
	}
	
	@Override
	public String getClassName() {
		return this.type.getName();
	}
	
	@Override
	public Class<?> getClassType() {
		return type;
	}
	
	@Override
	public Method[] getMethods() {
		if(methods == null){
			methods = getClassType().getDeclaredMethods();
		}
		return methods;
	}
	
	@Override
	public boolean isInterface() {
		return this.type.isInterface();
	}

	@Override
	public boolean isAnnotation() {
		return this.type.isAnnotation();
	}

	@Override
	public boolean isAbstract() {
		return Modifier.isAbstract(this.type.getModifiers());
	}

	@Override
	public boolean isConcrete() {
		return !(isInterface() || isAbstract());
	}

	@Override
	public boolean isFinal() {
		return Modifier.isFinal(this.type.getModifiers());
	}

	
	@Override
	public boolean hasSuperClass() {
		return this.type.getSuperclass() != null;
	}
	
	@Override
	public String getSuperClassName() {
		Class<?> supper = this.type.getSuperclass();
		return supper != null ? supper.getName() : null;
	}
	
	@Override
	public Class<?> getSuperClass() {
		return this.type.getSuperclass();
	}
	
	@Override
	public String[] getInterfaceNames() {
		Class<?>[] interfaces = this.type.getInterfaces();
		String[] names = new String[interfaces.length];
		for (int i = 0; i < interfaces.length; i++) {
			names[i] = interfaces[i].getName();
		}
		return names;
	}
	
	@Override
	public Class[] getInterfaces() {
		return type.getInterfaces();
	}
}
