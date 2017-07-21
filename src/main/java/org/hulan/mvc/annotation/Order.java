package org.hulan.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能描述：排序
 * 时间：2017/7/13 14:06
 * @author ：zhaokuiqiang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Order {
	int value() default Integer.MAX_VALUE;
}
