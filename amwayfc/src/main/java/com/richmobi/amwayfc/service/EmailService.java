package com.richmobi.amwayfc.service;

import java.util.List;

import com.richmobi.amwayfc.domain.User;

public interface EmailService {

	/**
	 * 
	 * 
	 * @param user
	 * @return
	 */
	public boolean sendEmail(User user,String password) throws Exception;

	public boolean sendEmail(List<User> us,String email) throws Exception;
	
}
