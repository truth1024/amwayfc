/**   
* @Title: EmailServiceTest.java
* @Package com.richmobi.amwayfc.service
* @Description: 
* @author Xuehan.Li
* @date 2014年3月21日 上午10:31:11
* @version V1.0
*/ 
package com.richmobi.amwayfc.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.richmobi.amwayfc.AbstractTestCase;
import com.richmobi.amwayfc.domain.User;

/** 
 * @ClassName: EmailServiceTest
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月21日 上午10:31:11
 * 
 */
public class EmailServiceTest extends AbstractTestCase{

	@Autowired
	EmailService emailService;
	@Autowired
	UserService userService;
	
	/** 
	 * @Title: setUp
	 * @Description: 
	 * @throws java.lang.Exception void
	 * @author Xuehan.Li
	 * @date 2014年3月21日 上午10:31:11
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.richmobi.amwayfc.service.EmailService#sendEmail(com.richmobi.amwayfc.domain.User)}.
	 * @throws Exception 
	 */
	@Test
	public void testSendEmail() throws Exception {
//		List<User> us = userService.getUsersByLogincode("190099");
//		List<User> us = userService.getUsersByLogincode("360647");
//		assertEquals(true, emailService.sendEmail(us,"xuehan.li@tendcloud.com"));
	}

}
