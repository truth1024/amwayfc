/**   
* @Title: LoginService.java
* @Package com.richmobi.amwayfc.service
* @Description: 
* @author Xuehan.Li
* @date 2014年3月17日 下午6:00:19
* @version V1.0
*/ 
package com.richmobi.amwayfc.service;

import java.util.List;

import com.richmobi.amwayfc.domain.Login;

/** 
 * @ClassName: LoginService
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月17日 下午6:00:19
 * 
 */
public interface LoginService {
	
	public void batchInsert(List<Login> ls) throws Exception;
	
	public Login getLogin(Login l);
	
	public void update(Login l);
	
	public Login getLoginByLogincode(String logincode);
	
	public void updateIsfirst(Login l);
}
