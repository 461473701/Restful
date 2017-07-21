package org.hulan.util;

/**
 * 功能描述：
 * 时间：2017/7/14 10:15
 * @author ：zhaokuiqiang
 */
public class Assert {
	
	public static void hasLength(String text){
		if(!StringUtils.hasLength(text)){
			throw new IllegalArgumentException();
		}
	}
	
	public static void hasLength(String text,String msg){
		if(!StringUtils.hasLength(text)){
			throw new IllegalArgumentException(msg);
		}
	}
	
}
