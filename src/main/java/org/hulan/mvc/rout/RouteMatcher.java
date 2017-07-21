package org.hulan.mvc.rout;

import io.netty.handler.codec.http.HttpMethod;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hulan.config.BeanDefinition;
import org.hulan.util.Assert;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述：url解析匹配
 * 时间：2017/7/14 16:18
 * @author ：zhaokuiqiang
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class RouteMatcher {
	
	/**
	 * 静态uri
	 */
	Map<String,Route> staticRouts = new ConcurrentHashMap<>();
	
	/**
	 * 动态uri
	 */
	Map<String,Route> dynamicRouts = new ConcurrentHashMap<>();
	
	/**
	 * 添加路由
	 * @param route
	 */
	public void addRoute(@NonNull Route route){
		Assert.hasLength(route.getPath());
		String path = route.getPath();
		path = parsePath(path);
		path = path + "#" + route.getHttpMethod().name();
		if(staticRouts.containsKey(path)) {
			throw new RuntimeException("包含相同的uri");
		}
		staticRouts.put(path, route);
	}
	
	/**
	 * 添加路由
	 * @param httpMethod
	 * @param path
	 * @param controller
	 * @param action
	 */
	public void addRoute(@NonNull HttpMethod httpMethod,@NonNull String path,@NonNull BeanDefinition controller,@NonNull Method action) {
		Assert.hasLength(path);
		Route route = new Route(path,httpMethod,controller,action,Integer.MAX_VALUE);
		addRoute(route);
	}
	
	/**
	 * 添加路由
	 * @param httpMethod
	 * @param path
	 * @param controller
	 * @param action
	 */
	public void addRoute(HttpMethod httpMethod, String [] path, BeanDefinition controller, Method action){
		for(String s : path) {
			addRoute(httpMethod,s,controller,action);
		}
	}
	
	/**
	 * 获取路由
	 * @param httpMethod
	 * @param path
	 * @return
	 */
	public Route getRoute(HttpMethod httpMethod,String path){
		Assert.hasLength(path);
		return staticRouts.get(parsePath(path));
	}
	
	/**
	 * 转换uri
	 * @param path
	 * @return
	 */
	public static String parsePath(String path){
		if(path == null){
			return "/";
		}
		if(path.length() == 0){
			return "/";
		}
		if(path.charAt(0) != '/'){
			path = "/" + path;
		}
		if(path.endsWith("/")){
			path = path.substring(0,path.length() - 1);
		}
		return path;
	}
}
