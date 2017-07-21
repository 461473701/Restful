package org.hulan.environment;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 * 功能描述：环境配置
 * 时间：2017/7/7 13:41
 * @author ：zhaokuiqiang
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Environment {

	Properties properties = new Properties();
	
	public static Environment empty(){
		return new Environment();
	}
	
	public static Environment of(InputStream in) {
		Environment environment = new Environment();
		try {
			environment.properties.load(in);
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
		return environment;
	}
	
	public static Environment of(String location) {
		InputStream in = getClassLoader().getResourceAsStream(location);
		if(in == null){
			throw new RuntimeException("文件未找到:"+location);
		}
		return of(in);
	}
	
	private static ClassLoader getClassLoader(){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if(classLoader == null){
			classLoader = Environment.class.getClassLoader();
		}
		return classLoader;
	}
	
	public Environment add(@NonNull String key,@NonNull Object value){
		properties.put(key,value);
		return this;
	}
	
	public Environment addAll(@NonNull Properties properties){
		properties.forEach((k, v) -> this.properties.setProperty(String.valueOf(k),String.valueOf(v)));
		return this;
	}
	
	public Environment addAll(@Nonnull Map<String,Object> properties){
		properties.forEach((k, v) -> this.properties.setProperty(k,String.valueOf(v)));
		return this;
	}
	
	public String getString(@Nonnull String key,String defaultValue){
		return Optional.ofNullable(this.properties.getProperty(key)).orElse(defaultValue);
	}
	
	public String getString(@Nonnull String key){
		return this.properties.getProperty(key);
	}
	
	public Integer getInt(@Nonnull String key,int defaultValue){
		return Integer.parseInt(getString(key, String.valueOf(defaultValue)));
	}
	
	public Integer getInt(@Nonnull String key){
		Optional optional = Optional.ofNullable(this.properties.getProperty(key));
		if(!optional.isPresent()){
			return null;
		}
		return Integer.parseInt(optional.get().toString());
	}
	
	public Long getLong(@Nonnull String key,long defaultValue){
		return Long.parseLong(getString(key, String.valueOf(defaultValue)));
	}
	
	public Long getLong(@Nonnull String key){
		Optional optional = Optional.ofNullable(this.properties.getProperty(key));
		if(!optional.isPresent()){
			return null;
		}
		return Long.parseLong(optional.get().toString());
	}
	
	public Boolean getBoolean(@Nonnull String key,boolean defaultValue){
		return Boolean.parseBoolean(getString(key, String.valueOf(defaultValue)));
	}
	
	public Boolean getBoolean(@Nonnull String key){
		Optional optional = Optional.ofNullable(this.properties.getProperty(key));
		if(!optional.isPresent()){
			return null;
		}
		return Boolean.parseBoolean(optional.get().toString());
	}
}
