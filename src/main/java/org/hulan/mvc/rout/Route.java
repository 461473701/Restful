package org.hulan.mvc.rout;

import io.netty.handler.codec.http.HttpMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hulan.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * 功能描述：
 * 时间：2017/7/14 15:39
 * @author ：zhaokuiqiang
 */
@Data
@AllArgsConstructor
public class Route {
	/**
	 * 路径
	 */
	String path;
	
	HttpMethod httpMethod;
	
	BeanDefinition target;
	
	Method action;
	
	int sort = Integer.MAX_VALUE;
	/*
	public Route(String path, HttpMethod httpMethod, Method action) {
		this.path = path;
		this.httpMethod = httpMethod;
		this.action = action;
	}
	
	public Route(String path, HttpMethod httpMethod, BeanDefinition target, Method action) {
		this.path = path;
		this.httpMethod = httpMethod;
		this.target = target;
		this.action = action;
	}
	
	public Route(String path, HttpMethod httpMethod, BeanDefinition target, Method action, int sort) {
		this.path = path;
		this.httpMethod = httpMethod;
		this.target = target;
		this.action = action;
		this.sort = sort;
	}*/
}
