package com.richmobi.amwayfc.service;

import com.richmobi.amwayfc.domain.User;

public interface EmailService {

	/**
	 * 
	 * 
	 * @param user
	 * @return
	 */
	public boolean sendEmail(User user) throws Exception;
	
}
