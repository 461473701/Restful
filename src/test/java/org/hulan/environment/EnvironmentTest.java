package org.hulan.environment;

import java.io.IOException;

/**
 * 功能描述：
 * 时间：2017/7/7 14:01
 * @author ：zhaokuiqiang
 */
public class EnvironmentTest {
	
	public static void main(String[] args) {
		Environment environment = null;
		environment = Environment.of("app.properties");
		System.out.println(environment.getInt("a"));
	}
}
