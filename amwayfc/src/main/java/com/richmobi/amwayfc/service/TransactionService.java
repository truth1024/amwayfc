/**   
* @Title: TransactionService.java
* @Package com.richmobi.amwayfc.service
* @Description: 
* @author Xuehan.Li
* @date 2014年3月28日 下午3:34:26
* @version V1.0
*/ 
package com.richmobi.amwayfc.service;

import java.util.List;
import java.util.Map;

import com.richmobi.amwayfc.domain.UserJourney;

/** 
 * @ClassName: TransactionService
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月28日 下午3:34:26
 * 
 */
public interface TransactionService {
	public String insertJourney(String logincode,List<UserJourney> ujs,Map<Long,Integer> numMap)throws Exception;
}
