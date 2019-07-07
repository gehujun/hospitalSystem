package com.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 字符编码的过滤器
 * @author ASUS
 *
 */
public class CharacterEncondingFilter implements Filter {

	@Override
	public void destroy() {

	}

	/**
	 * 字符编码处理
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filter)
			throws IOException, ServletException {

		  req.setCharacterEncoding("UTF-8");
		  resp.setCharacterEncoding("UTF-8");
		
		  filter.doFilter(req, resp);//过滤处理
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
