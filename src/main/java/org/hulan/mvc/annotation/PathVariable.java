package org.hulan.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能描述：参数对应
 * 时间：2017/7/7 15:35
 * @author ：zhaokuiqiang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PathVariable {
	String value() default "";
}
