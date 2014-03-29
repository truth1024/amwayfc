/**   
* @Title: UserJourneyService.java
* @Package com.richmobi.amwayfc.service
* @Description: 
* @author Xuehan.Li
* @date 2014年3月18日 下午4:38:45
* @version V1.0
*/ 
package com.richmobi.amwayfc.service;

import java.util.List;

import com.richmobi.amwayfc.domain.UserJourney;

/** 
 * @ClassName: UserJourneyService
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月18日 下午4:38:45
 * 
 */
public interface UserJourneyService {
	
	public void batchInsert(List<UserJourney> js);
	
	public void deleteByUid(long uid);
	
	public void deleteByLogincode(String logincode);
	
	public List<Long> getUidByAuid(long auid);
	
	public List<UserJourney> getByLogincode(String logincode);
}
