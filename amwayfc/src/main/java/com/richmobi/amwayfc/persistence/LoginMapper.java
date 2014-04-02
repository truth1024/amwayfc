/**   
* @Title: LoginMapper.java
* @Package com.richmobi.amwayfc.persistence
* @Description: 
* @author Xuehan.Li
* @date 2014年3月17日 下午5:45:48
* @version V1.0
*/ 
package com.richmobi.amwayfc.persistence;

import java.util.List;

import com.richmobi.amwayfc.domain.Login;

/** 
 * @ClassName: LoginMapper
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月17日 下午5:45:48
 * 
 */
public interface LoginMapper {

	void batchInsert(List<Login> ls);
	
	Login getLogin(Login l);
	
	Login getLoginByLogincode(String logincode);
	
	void update(Login l);
	
	void updateIsfirst(Login l);
	
}
