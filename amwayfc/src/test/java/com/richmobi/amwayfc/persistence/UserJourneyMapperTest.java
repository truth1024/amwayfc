/**   
* @Title: UserJourneyMapperTest.java
* @Package com.richmobi.amwayfc.persistence
* @Description: 
* @author Xuehan.Li
* @date 2014年3月29日 下午12:00:26
* @version V1.0
*/ 
package com.richmobi.amwayfc.persistence;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.richmobi.amwayfc.AbstractTestCase;

/** 
 * @ClassName: UserJourneyMapperTest
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月29日 下午12:00:26
 * 
 */
public class UserJourneyMapperTest extends AbstractTestCase {

	@Autowired
	UserJourneyMapper userJourneyMapper;
	
	/** 
	 * @Title: setUp
	 * @Description: 
	 * @throws java.lang.Exception void
	 * @author Xuehan.Li
	 * @date 2014年3月29日 下午12:00:26
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.richmobi.amwayfc.persistence.UserJourneyMapper#getParnumsBylogincode(java.lang.String)}.
	 */
	@Test
	public void testGetParnumsBylogincode() {
		System.out.println(userJourneyMapper.getParnumsBylogincode("245575"));
	}

}
