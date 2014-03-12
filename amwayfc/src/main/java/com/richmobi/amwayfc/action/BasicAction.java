package com.richmobi.amwayfc.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BasicAction extends ActionSupport {

	private static final long serialVersionUID = -6390491233685056370L;
	
	
	protected String getContextURL(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String reqURL = request.getRequestURL().toString();
		String reqURI = request.getRequestURI();
		return reqURL.substring(0, reqURL.indexOf(reqURI)) + request.getContextPath();
	}
}
