package com.richmobi.amwayfc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.richmobi.amwayfc.domain.Login;
import com.richmobi.amwayfc.util.Constant;

public class AuthFilter implements Filter{

	private static final Logger log = Logger.getLogger(AuthFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		// 获取请求url
		String requestURL = httpServletRequest.getRequestURL().toString();
		log.debug("requestURL:" + requestURL);
		if(request instanceof HttpServletRequest && response instanceof HttpServletResponse){
			HttpServletRequest req = (HttpServletRequest)request;
			HttpServletResponse res = (HttpServletResponse) response;
			Login login = (Login) req.getSession().getAttribute(Constant.SESSION_NAME);
			log.debug("login : "+login);
			String accept = req.getHeader("Accept");
			log.debug("accept : " + accept);
			if(requestURL.indexOf("step") < 0){
				chain.doFilter(request, response);
			}else{
				if(login == null || login.getIsfirst() == 1){
					if (accept.indexOf("application/json") > -1) {
						res.setStatus(401);
					} else {
						res.sendRedirect("index.html");
					}
				} else {
					chain.doFilter(request, response);
				}
			}
		} else {
			request.getRequestDispatcher("error.html");
		}
	}

	@Override
	public void destroy() {
		
	}

}
