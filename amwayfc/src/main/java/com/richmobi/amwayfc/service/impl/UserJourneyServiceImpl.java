/**   
* @Title: UserJourneyServiceImpl.java
* @Package com.richmobi.amwayfc.service.impl
* @Description: 
* @author Xuehan.Li
* @date 2014年3月18日 下午4:39:36
* @version V1.0
*/ 
package com.richmobi.amwayfc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richmobi.amwayfc.domain.UserJourney;
import com.richmobi.amwayfc.persistence.JourneyMapper;
import com.richmobi.amwayfc.persistence.UserJourneyMapper;
import com.richmobi.amwayfc.service.UserJourneyService;

/** 
 * @ClassName: UserJourneyServiceImpl
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月18日 下午4:39:36
 * 
 */
@Service
public class UserJourneyServiceImpl implements UserJourneyService {

	@Autowired
	UserJourneyMapper userJourneyMapper;
	@Autowired
	JourneyMapper journeyMapper;
	
	/**
	 * @Title: batchInsert
	 * @Description:
	 * @param js
	 * @author Xuehan.Li
	 * @date 2014年3月18日 下午4:39:36
	 * @see com.richmobi.amwayfc.service.UserJourneyService#batchInsert(java.util.List)
	 */
	@Override
	public void batchInsert(List<UserJourney> js) {
		userJourneyMapper.batchInsert(js);
	}

	/**
	 * @Title: deleteByUid
	 * @Description:
	 * @param uid
	 * @author Xuehan.Li
	 * @date 2014年3月18日 下午4:39:36
	 * @see com.richmobi.amwayfc.service.UserJourneyService#deleteByUid(long)
	 */
	@Override
	public void deleteByUid(long uid) {
		userJourneyMapper.deleteByUid(uid);
	}

	/**
	 * @Title: deleteByLogincode
	 * @Description:
	 * @param logincode
	 * @author Xuehan.Li
	 * @date 2014年3月18日 下午4:39:36
	 * @see com.richmobi.amwayfc.service.UserJourneyService#deleteByLogincode(java.lang.String)
	 */
	@Override
	public void deleteByLogincode(String logincode) {
		userJourneyMapper.deleteByLogincode(logincode);
	}

	/**
	 * @Title: getUidByAuid
	 * @Description:
	 * @param auid
	 * @return
	 * @author Xuehan.Li
	 * @date 2014年3月18日 下午4:39:36
	 * @see com.richmobi.amwayfc.service.UserJourneyService#getUidByAuid(long)
	 */
	@Override
	public List<Long> getUidByAuid(long auid) {
		return userJourneyMapper.getUidByAuid(auid);
	}

	/**
	* @Title: getByLogincode
	* @Description:根据户籍编号获取用户行程关系
	* @param logincode
	* @return
	* @author Xuehan.Li
	* @date 2014年3月29日 下午5:08:59
	* @see com.richmobi.amwayfc.service.UserJourneyService#getByLogincode(java.lang.String)
	*/ 
	@Override
	public List<UserJourney> getByLogincode(String logincode) {
		return userJourneyMapper.getByLogincode(logincode);
	}

}
