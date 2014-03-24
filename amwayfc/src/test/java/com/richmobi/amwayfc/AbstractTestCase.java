package com.richmobi.amwayfc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.richmobi.amwayfc.domain.User;

/** 
* @ClassName: AbstractTestCase 
* @Description: 单元测试抽象类，配置测试环境
* @author shuangfei.zhang
* @date 2012-3-21 下午2:09:42 
*  
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml","/applicationContext-email.xml","/applicationContext_sms_rpc.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class AbstractTestCase {
	@Test
	public void test(){
		
	}

	protected static final User user = new User();
	static{
		user.setPhone("13439401564");
		user.setEmail("truth88@qq.com");
		user.setName("李学瀚");
	}
	
	
}
