package org.hulan.util;

/**
 * 功能描述：反射相关辅助类
 * 时间：2017/7/14 12:55
 * @author ：zhaokuiqiang
 */
public class ReflectUtil {
	static String s = "&";
	/**
	 * 转换类名为别名(首字母小写)
	 * @param type
	 * @return
	 */
	public static String transformClassName(Class<?> type){
		String simpleName = type.getSimpleName();
		return s+Character.toLowerCase(simpleName.charAt(0))+simpleName.substring(1,simpleName.length());
	}
	
	/**
	 * 转换类名为别名(首字母小写)
	 * @param type
	 * @return
	 */
	public static String transformClassName2(Class<?> type){
		StringBuilder sb = new StringBuilder(s);
		String simpleName = type.getSimpleName();
		return sb.append(Character.toLowerCase(simpleName.charAt(0))).append(simpleName.substring(1,simpleName.length())).toString();
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for(int i = 0; i < 1000000; i++) {
			transformClassName2(ReflectUtil.class);
		}
		System.out.println(System.currentTimeMillis() - start);
	}
}
