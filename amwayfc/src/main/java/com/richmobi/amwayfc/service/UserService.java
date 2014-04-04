/**   
* @Title: UserService.java
* @Package com.richmobi.amwayfc.service
* @Description: 
* @author Xuehan.Li
* @date 2014年3月18日 上午11:26:51
* @version V1.0
*/ 
package com.richmobi.amwayfc.service;

import java.util.List;
import java.util.Map;

import com.richmobi.amwayfc.domain.PageResult;
import com.richmobi.amwayfc.domain.User;

/** 
 * @ClassName: UserService
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月18日 上午11:26:51
 * 
 */
public interface UserService {
	
	/** 
	* @Title: insert 
	* @Description: 插入一条用户信息
	* @param user  用户信息
	* @return 
	* @throws 
	*/
	public long insert(User user);
	/** 
	* @Title: batchInsertUser 
	* @Description: 批量插入用户信息
	* @param users  用户信息列表
	* @return 
	* @throws 
	*/
	public void batchInsertUser(List<User> users);
	
	/**
	* @Title: updateUser
	* @Description: 更新用户信息
	* @param user 用户信息
	* @throws
	*/
	public void update(User user);
	
	
	/** 
	* @Title: getUsersByCondition
	* @Description: 获取用户列表（可以无条件Map，分页）
	* @param condition
	* @return List<User>
	* @author Xuehan.Li
	* @date 2014年3月18日 上午11:04:34
	*/ 
	public PageResult<User> getUsersByCondition(Map<String,Object> condition,int page,int row);
	
	/** 
	* @Title: getUsersByLogincode
	* @Description: 根据户籍编号获取用户列表
	* @param logincode
	* @return User
	* @author Xuehan.Li
	* @date 2014年3月18日 上午11:06:03
	*/ 
	public List<User> getUsersByLogincode(String logincode,int isjoin);
}
