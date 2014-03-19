/**   
* @Title: JourneyMapper.java
* @Package com.richmobi.amwayfc.persistence
* @Description: 
* @author Xuehan.Li
* @date 2014年3月18日 下午3:31:37
* @version V1.0
*/ 
package com.richmobi.amwayfc.persistence;

import java.util.List;

import com.richmobi.amwayfc.domain.Journey;

/** 
 * @ClassName: JourneyMapper
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月18日 下午3:31:37
 * 
 */
public interface JourneyMapper {

	List<Journey> getJourneysByUid(long uid);
	
	int getRemainNumById(long id);
	
	void updateJoinNum(long id);
	
	List<Journey> getJourneys();
}
