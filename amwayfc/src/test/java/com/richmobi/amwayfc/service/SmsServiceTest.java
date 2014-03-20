package com.richmobi.amwayfc.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.richmobi.amwayfc.AbstractTestCase;

public class SmsServiceTest extends AbstractTestCase{
	
	@Autowired
	SmsService smsService;
	
	
	@Before
	public void setUp(){
		user.setId(1L);
	}
	
	@Test
	public void testSendSMS(){
		Assert.assertEquals(true, smsService.sendSMS(user, "李学瀚先生您好，您的登录密码为：12ld03k 【安利FC】"));
	}
	
	@Test
	public void testSendNotificationSMS(){
		smsService.sendNotificationSMS(user);
	}
	
	@Test
	public void testSendSuccessSMS(){
		smsService.sendSuccessSMS(user);
	}

}
