package org.hulan.mvc.filter;

import io.netty.handler.codec.http.HttpRequest;

/**
 * 功能描述：过滤器
 * 时间：2017/7/14 15:38
 * @author ：zhaokuiqiang
 */
public abstract class Filter {

	public boolean before(HttpRequest request){return true;}

	public boolean after(HttpRequest request){return true;}
}
