package org.hulan.bean;

import org.hulan.Rest;

/**
 * 功能描述：
 * 时间：2017/7/14 13:39
 * @author ：zhaokuiqiang
 */
public class BeanFactoryTest {
	
	public void test(){
		BeanFactory factory = new DefaultBeanFactory();
		factory.addBean(Rest.class);
		System.out.println(factory.getBean("app"));
		System.out.println(factory.getBean(Rest.class));
	}
	
	public static void main(String[] args) {
		BeanFactoryTest test = new BeanFactoryTest();
		test.test();
	}
}
