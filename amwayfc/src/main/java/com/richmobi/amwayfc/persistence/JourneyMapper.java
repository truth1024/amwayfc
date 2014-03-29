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
import java.util.Map;

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
	
	List<Journey> getRemainNum();
	
	void updateJoinNum(Map<String,Object> map);
	
	List<Journey> getJourneys();
}
