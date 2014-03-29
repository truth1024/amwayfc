/**   
* @Title: TransactionServiceImpl.java
* @Package com.richmobi.amwayfc.service.impl
* @Description: 
* @author Xuehan.Li
* @date 2014年3月28日 下午3:37:40
* @version V1.0
*/ 
package com.richmobi.amwayfc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.richmobi.amwayfc.domain.Journey;
import com.richmobi.amwayfc.domain.UserJourney;
import com.richmobi.amwayfc.persistence.JourneyMapper;
import com.richmobi.amwayfc.persistence.UserJourneyMapper;
import com.richmobi.amwayfc.service.TransactionService;

/** 
 * @ClassName: TransactionServiceImpl
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月28日 下午3:37:40
 * 
 */
@Transactional
@Service
public class TransactionServiceImpl implements TransactionService {

	private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	@Autowired
	UserJourneyMapper userJourneyMapper;
	@Autowired
	JourneyMapper journeyMapper;
	
	/**
	* @Title: insertJourney
	* @Description:
	* @param logincode
	* @param ujs
	* @param numMap
	* @return
	* @throws Exception
	* @author Xuehan.Li
	* @date 2014年3月28日 下午3:42:21
	* @see com.richmobi.amwayfc.service.TransactionService#insertJourney(java.lang.String, java.util.List, java.util.Map)
	*/ 
	@Override
	public String insertJourney(String logincode, List<UserJourney> ujs,
			Map<Long, Integer> numMap){
		String tip = "success";
		try {
			List<UserJourney> parnums = userJourneyMapper.getParnumsBylogincode(logincode);
			Map<Long,Integer> numMap2 = new HashMap<Long, Integer>();
			for(UserJourney pn : parnums){
				numMap2.put(pn.getJid(), pn.getParnum());
			}
			log.debug("numMap2 : {}",numMap2);
			//各行程剩余人数
			List<Journey> rns = journeyMapper.getRemainNum();
			List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
			//判断有人数限制的行程剩余人数是否够用
			for(Journey r : rns){
				long jid = r.getId();
				String remainnumstr = r.getRemainnum();
				int deletenum = 0;
				if(numMap2.get(jid) != null){
					deletenum = numMap2.get(jid);
				}
				log.debug("title: {};parnum : {};remainnum : {};deletenum : {}",
						new Object[]{r.getTitle(),numMap.get(jid),remainnumstr,deletenum});
				int remainnum = Integer.parseInt(remainnumstr);
				int parnum = deletenum-(numMap.get(jid) != null ? numMap.get(jid) : 0);
				//剩余人数不够使
				if(remainnum < -parnum){
					throw new Exception(r.getTitle()+"剩余人数不够");
				}else{
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("id",jid);
					map.put("num",parnum);
					mapList.add(map);
				}
			}
			//删除用户行程
			userJourneyMapper.deleteByLogincode(logincode);
			//插入用户行程
			userJourneyMapper.batchInsert(ujs);
			//更新剩余人数
			for(Map<String,Object> map : mapList){
				journeyMapper.updateJoinNum(map);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			tip = e.getMessage();
			e.printStackTrace();
		}
		return tip;
	}
	
	

}
