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
		// 문자 인코딩 필터
		req.setCharacterEncoding(charset);
		
		// 다음 필터 또는 마지막이면 jsp/servlet 실행
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
