/**   
* @Title: JourneyServiceImpl.java
* @Package com.richmobi.amwayfc.service.impl
* @Description: 
* @author Xuehan.Li
* @date 2014年3月27日 上午10:10:19
* @version V1.0
*/ 
package com.richmobi.amwayfc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richmobi.amwayfc.domain.Journey;
import com.richmobi.amwayfc.persistence.JourneyMapper;
import com.richmobi.amwayfc.service.JourneyService;

/** 
 * @ClassName: JourneyServiceImpl
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月27日 上午10:10:19
 * 
 */
@Service
public class JourneyServiceImpl implements JourneyService {

	@Autowired
	JourneyMapper journeyMapper;
	
	/**
	 * @Title: getJourneysByUid
	 * @Description:根据用户ID获取行程列表
	 * @param uid
	 * @return
	 * @author Xuehan.Li
	 * @date 2014年3月27日 上午10:10:19
	 * @see com.richmobi.amwayfc.service.JourneyService#getJourneysByUid(long)
	 */
	@Override
	public List<Journey> getJourneysByUid(long uid) {
		return journeyMapper.getJourneysByUid(uid);
	}

	/**
	 * @Title: getRemainNumById
	 * @Description:根据行程ID获取剩余人数
	 * @param id
	 * @return
	 * @author Xuehan.Li
	 * @date 2014年3月27日 上午10:10:19
	 * @see com.richmobi.amwayfc.service.JourneyService#getRemainNumById(long)
	 */
	@Override
	public int getRemainNumById(long id) {
		return journeyMapper.getRemainNumById(id);
	}

	/**
	 * @Title: updateJoinNum
	 * @Description:更新剩余人数
	 * @param id
	 * @author Xuehan.Li
	 * @date 2014年3月27日 上午10:10:19
	 * @see com.richmobi.amwayfc.service.JourneyService#updateJoinNum(long)
	 */
	@Override
	public void updateJoinNum(long id) {
		journeyMapper.updateJoinNum(id);
	}

	/**
	 * @Title: getJourneys
	 * @Description:获取全部自选行程
	 * @return
	 * @author Xuehan.Li
	 * @date 2014年3月27日 上午10:10:19
	 * @see com.richmobi.amwayfc.service.JourneyService#getJourneys()
	 */
	@Override
	public List<Journey> getJourneys() {
		return journeyMapper.getJourneys();
	}

}
