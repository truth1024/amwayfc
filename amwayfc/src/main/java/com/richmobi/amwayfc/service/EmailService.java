package com.richmobi.amwayfc.service;

import javax.mail.SendFailedException;

import com.richmobi.amwayfc.domain.User;

public interface EmailService {

	/**
	 * 
	 * 
	 * @param user
	 * @return
	 */
	public boolean sendEmail(User user) throws SendFailedException;
	
}
