package org.hulan;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hulan.bean.BeanFactory;
import org.hulan.bean.DefaultBeanFactory;
import org.hulan.mvc.rout.RouteMatcher;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Rest {

	RouteMatcher routeMatcher = new RouteMatcher();
	
	BeanFactory beanFactory = new DefaultBeanFactory();
	
	public Rest me(){
		return new Rest();
	}
	
	
}
