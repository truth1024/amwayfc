package com.richmobi.amwayfc.service;

import com.richmobi.amwayfc.domain.User;

public interface SmsService {
	
	/**
	* @Title: sendSMS
	* @Description: 给手机发短信
	* @param phone
	* @return
	* @throws
	*/
	public boolean sendSMS(User user,String msg);
	
	/**
	 * 发送通知短信
	 * 
	 * @param user
	 * @return
	 */
	public boolean sendNotificationSMS(User user);
	
	/**
	 * 创建签到成功短信
	 * 
	 * @param user
	 * @return
	 */
	public boolean sendSuccessSMS(User user);
	
}
