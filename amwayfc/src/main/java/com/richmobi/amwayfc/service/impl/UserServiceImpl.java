/**   
* @Title: UserServiceImpl.java
* @Package com.richmobi.amwayfc.service.impl
* @Description: 
* @author Xuehan.Li
* @date 2014年3月18日 上午11:28:25
* @version V1.0
*/ 
package com.richmobi.amwayfc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richmobi.amwayfc.domain.PageResult;
import com.richmobi.amwayfc.domain.User;
import com.richmobi.amwayfc.persistence.UserMapper;
import com.richmobi.amwayfc.service.UserService;

/** 
 * @ClassName: UserServiceImpl
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月18日 上午11:28:25
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	/**
	 * @Title: insert
	 * @Description:
	 * @param user
	 * @return
	 * @author Xuehan.Li
	 * @date 2014年3月18日 上午11:28:26
	 * @see com.richmobi.amwayfc.service.UserService#insert(com.richmobi.amwayfc.domain.User)
	 */
	@Override
	public long insert(User user) {
		userMapper.insert(user);
		return user.getId();
	}

	/**
	 * @Title: batchInsertUser
	 * @Description:
	 * @param users
	 * @author Xuehan.Li
	 * @date 2014年3月18日 上午11:28:26
	 * @see com.richmobi.amwayfc.service.UserService#batchInsertUser(java.util.List)
	 */
	@Override
	public void batchInsertUser(List<User> users) {
		userMapper.batchInsertUser(users);
	}

	/**
	 * @Title: update
	 * @Description:
	 * @param user
	 * @author Xuehan.Li
	 * @date 2014年3月18日 上午11:28:26
	 * @see com.richmobi.amwayfc.service.UserService#update(com.richmobi.amwayfc.domain.User)
	 */
	@Override
	public void update(User user) {
		userMapper.update(user);
	}

	/**
	 * @Title: getUsersByCondition
	 * @Description:
	 * @param condition
	 * @return
	 * @author Xuehan.Li
	 * @date 2014年3月18日 上午11:28:26
	 * @see com.richmobi.amwayfc.service.UserService#getUsersByCondition(java.util.Map)
	 */
	@Override
	public PageResult<User> getUsersByCondition(Map<String, Object> condition,int page,int row) {
		PageResult<User> pr = new PageResult<User>(page, row);
		if(condition == null){
			condition = new HashMap<String,Object>();
		}
		if(page > 0 && row > 0){
			condition.put("offset", (long) pr.getStart());
			condition.put("row", (long) row);
		}
		List<User> Users =  userMapper.getUsersByCondition(condition);
		pr.setData(Users);
		return pr;
	}

	/**
	 * @Title: getUsersByLogincode
	 * @Description:
	 * @param logincode
	 * @return
	 * @author Xuehan.Li
	 * @date 2014年3月18日 上午11:28:26
	 * @see com.richmobi.amwayfc.service.UserService#getUsersByLogincode(java.lang.String)
	 */
	@Override
	public List<User> getUsersByLogincode(String logincode,int isjoin) {
		User user = new User();
		user.setLogincode(logincode);
		user.setIsjoin(isjoin);
		return userMapper.getUsersByLogincode(user);
	}

}
