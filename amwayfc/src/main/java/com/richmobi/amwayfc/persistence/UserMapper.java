package com.richmobi.amwayfc.persistence;

import java.util.List;
import java.util.Map;

import com.richmobi.amwayfc.domain.User;

/** 
* @ClassName: UserMapper 
* @Description: 提供用户信息的CRUD
* @author shuangfei.zhang
* @date 2012-3-21 下午2:32:58 
*  
*/
public interface UserMapper {
	
	/** 
	* @Title: insert 
	* @Description: 插入一条用户信息
	* @param user  用户信息
	* @return 
	* @throws 
	*/
	int insert(User user);
	/** 
	* @Title: batchInsertUser 
	* @Description: 批量插入用户信息
	* @param users  用户信息列表
	* @return 
	* @throws 
	*/
	void batchInsertUser(List<User> users);
	
	/**
	* @Title: updateUser
	* @Description: 更新用户信息
	* @param user 用户信息
	* @throws
	*/
	void update(User user);
	
	
	/** 
	* @Title: getUsersByCondition
	* @Description: 获取用户列表（可以无条件Map，分页）
	* @param condition
	* @return List<User>
	* @author Xuehan.Li
	* @date 2014年3月18日 上午11:04:34
	*/ 
	List<User> getUsersByCondition(Map<String,Object> condition);
	
	/** 
	* @Title: getUsersByLogincode
	* @Description: 根据户籍编号获取用户列表
	* @param logincode
	* @return User
	* @author Xuehan.Li
	* @date 2014年3月18日 上午11:06:03
	*/ 
	List<User> getUsersByLogincode(String logincode);
}
