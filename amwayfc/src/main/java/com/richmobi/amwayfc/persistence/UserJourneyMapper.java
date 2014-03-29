/**   
* @Title: UserJourneyMapper.java
* @Package com.richmobi.amwayfc.persistence
* @Description: 
* @author Xuehan.Li
* @date 2014年3月18日 下午4:22:14
* @version V1.0
*/ 
package com.richmobi.amwayfc.persistence;

import java.util.List;

import com.richmobi.amwayfc.domain.UserJourney;

/** 
 * @ClassName: UserJourneyMapper
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月18日 下午4:22:14
 * 
 */
public interface UserJourneyMapper {

	void batchInsert(List<UserJourney> js);
	
	void deleteByUid(long uid);
	
	void deleteByLogincode(String logincode);
	
	List<Long> getUidByAuid(long auid);
	
	List<UserJourney> getParnumsBylogincode(String logincode);
	
	List<UserJourney> getByLogincode(String logincode);
}
