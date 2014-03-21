package com.richmobi.amwayfc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.richmobi.amwayfc.domain.Login;
import com.richmobi.amwayfc.util.Constant;

public class BasicAction extends ActionSupport implements ServletRequestAware,  ServletResponseAware{

	private static final long serialVersionUID = -6390491233685056370L;
	
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	
	protected String getContextURL(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String reqURL = request.getRequestURL().toString();
		String reqURI = request.getRequestURI();
		return reqURL.substring(0, reqURL.indexOf(reqURI)) + request.getContextPath();
	}


	
	protected Login getSessionLogin(){
		Object obj = request.getSession().getAttribute(Constant.SESSION_NAME);
		return obj == null ? null : (Login) obj;
	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
