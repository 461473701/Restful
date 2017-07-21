package org.hulan.mvc.rout;

import io.netty.handler.codec.http.HttpMethod;
import lombok.NonNull;
import org.hulan.config.BeanDefinition;
import org.hulan.mvc.annotation.GetMapping;
import org.hulan.mvc.annotation.PostMapping;
import org.hulan.mvc.annotation.RequestMapping;
import org.hulan.util.Assert;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 功能描述：路由建造器
 * 时间：2017/7/15 10:07
 * @author ：zhaokuiqiang
 */
public class RouteResolver {
	
	RouteMatcher routeMatcher;
	
	public RouteResolver(RouteMatcher routeMatcher) {
		this.routeMatcher = routeMatcher;
	}
	
	/**
	 * 路由解析
	 * @param controller
	 */
	public void routeResolver(@NonNull BeanDefinition controller){
		RequestMapping mapping = controller.getAnnotation(RequestMapping.class);
		String [] prefix = mapping.value();
		for(String s : prefix) {
			if(mapping != null){
				s = routeMatcher.parsePath(s);
			}
			requestMappingBuilder(s,controller);
			getMappingBuilder(s,controller);
			postMappingBuilder(s,controller);
		}
	}
	
	/**
	 * 解析requestmapping
	 * @param prefixPath
	 * @param controller
	 */
	protected void requestMappingBuilder(String prefixPath,BeanDefinition controller){
		List<Method> methods = controller.getAnnotatedMethods(RequestMapping.class);
		methods.forEach(method -> {
			RequestMapping mapping = method.getAnnotation(RequestMapping.class);
			String path;
			for(String s : mapping.value()) {
				if(prefixPath != null){
					path = prefixPath + routeMatcher.parsePath(s);
				}else{
					path = routeMatcher.parsePath(s);
				}
				routeMatcher.addRoute(HttpMethod.GET,path,controller,method);
				routeMatcher.addRoute(HttpMethod.POST,path,controller,method);
			}
		});
	}
	
	/**
	 * 解析getmapping
	 * @param prefixPath
	 * @param controller
	 */
	protected void getMappingBuilder(String prefixPath,BeanDefinition controller){
		List<Method> methods = controller.getAnnotatedMethods(GetMapping.class);
		methods.forEach(method -> {
			GetMapping mapping = method.getAnnotation(GetMapping.class);
			String path;
			for(String s : mapping.value()) {
				if(prefixPath != null){
					path = prefixPath + routeMatcher.parsePath(s);
				}else{
					path = routeMatcher.parsePath(s);
				}
				routeMatcher.addRoute(HttpMethod.GET,path,controller,method);
			}
		});
	}
	
	/**
	 * 解析postmapping
	 * @param prefixPath
	 * @param controller
	 */
	protected void postMappingBuilder(String prefixPath,BeanDefinition controller){
		List<Method> methods = controller.getAnnotatedMethods(PostMapping.class);
		methods.forEach(method -> {
			PostMapping mapping = method.getAnnotation(PostMapping.class);
			String path;
			for(String s : mapping.value()) {
				if(prefixPath != null){
					path = prefixPath + routeMatcher.parsePath(s);
				}else{
					path = routeMatcher.parsePath(s);
				}
				routeMatcher.addRoute(HttpMethod.POST,path,controller,method);
			}
		});
	}
}
