package org.hulan.mvc.annotation;

import io.netty.handler.codec.http.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能描述：请求对应get|post
 * 时间：2017/7/7 15:19
 * @author ：zhaokuiqiang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface RequestMapping {
	String [] value() default "/";
}
