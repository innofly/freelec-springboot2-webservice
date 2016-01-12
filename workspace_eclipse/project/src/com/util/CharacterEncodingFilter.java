package com.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
	private String charset;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException
	{
		// ���� ���ڵ� ����
		req.setCharacterEncoding(charset);
		
		// ���� ���� �Ǵ� �������̸� jsp/servlet ����
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException
	{
		charset=config.getInitParameter("charset");
		
		if(charset == null)
			charset="utf-8";
	}
}
