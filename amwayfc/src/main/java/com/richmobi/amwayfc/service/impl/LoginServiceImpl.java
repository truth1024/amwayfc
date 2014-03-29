/**   
* @Title: LoginServiceImpl.java
* @Package com.richmobi.amwayfc.service.impl
* @Description: 
* @author Xuehan.Li
* @date 2014年3月17日 下午6:01:32
* @version V1.0
*/ 
package com.richmobi.amwayfc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richmobi.amwayfc.domain.Login;
import com.richmobi.amwayfc.persistence.LoginMapper;
import com.richmobi.amwayfc.service.LoginService;

/** 
 * @ClassName: LoginServiceImpl
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月17日 下午6:01:32
 * 
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginMapper loginMapper;
	
	/**
	 * @Title: batchInsert
	 * @Description:
	 * @param ls
	 * @author Xuehan.Li
	 * @date 2014年3月17日 下午6:01:32
	 * @see com.richmobi.amwayfc.service.LoginService#batchInsert(java.util.List)
	 */
	@Override
	public void batchInsert(List<Login> ls) throws Exception {
		loginMapper.batchInsert(ls);
	}

	/**
	 * @Title: getLogin
	 * @Description:
	 * @param l
	 * @return
	 * @author Xuehan.Li
	 * @date 2014年3月17日 下午6:01:32
	 * @see com.richmobi.amwayfc.service.LoginService#getLogin(com.richmobi.amwayfc.domain.Login)
	 */
	@Override
	public Login getLogin(Login l) {
		return loginMapper.getLogin(l);
	}

	/**
	 * @Title: update
	 * @Description:
	 * @param l
	 * @author Xuehan.Li
	 * @date 2014年3月17日 下午6:01:32
	 * @see com.richmobi.amwayfc.service.LoginService#update(com.richmobi.amwayfc.domain.Login)
	 */
	@Override
	public void update(Login l) {
		loginMapper.update(l);
	}

	
	/**
	* @Title: getLoginByLogincode
	* @Description:根据户籍编号获取登录信息
	* @param logincode
	* @return
	* @author Xuehan.Li
	* @date 2014年3月29日 下午11:01:47
	* @see com.richmobi.amwayfc.service.LoginService#getLoginByLogincode(java.lang.String)
	*/ 
	@Override
	public Login getLoginByLogincode(String logincode) {
		return loginMapper.getLoginByLogincode(logincode);
	}

}
