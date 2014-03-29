/**   
* @Title: JourneyService.java
* @Package com.richmobi.amwayfc.service
* @Description: 
* @author Xuehan.Li
* @date 2014年3月27日 上午10:09:25
* @version V1.0
*/ 
package com.richmobi.amwayfc.service;

import java.util.List;

import com.richmobi.amwayfc.domain.Journey;

/** 
 * @ClassName: JourneyService
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月27日 上午10:09:25
 * 
 */
public interface JourneyService {

	public List<Journey> getJourneysByUid(long uid);
	
	public void updateJoinNum(long id, int num);
	
	public List<Journey> getJourneys();
}
