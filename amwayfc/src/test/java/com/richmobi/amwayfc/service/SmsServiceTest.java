package com.richmobi.amwayfc.service;

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
		smsService.sendSMS(user, "test");
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
