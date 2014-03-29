/**   
* @Title: JourneyAction.java
* @Package com.richmobi.amwayfc.action
* @Description: 
* @author Xuehan.Li
* @date 2014年3月27日 上午10:04:45
* @version V1.0
*/ 
package com.richmobi.amwayfc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.richmobi.amwayfc.domain.Journey;
import com.richmobi.amwayfc.domain.User;
import com.richmobi.amwayfc.domain.UserJourney;
import com.richmobi.amwayfc.service.JourneyService;
import com.richmobi.amwayfc.service.TransactionService;
import com.richmobi.amwayfc.service.UserJourneyService;
import com.richmobi.amwayfc.service.UserService;
import com.richmobi.amwayfc.util.Constant;

/** 
 * @ClassName: JourneyAction
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月27日 上午10:04:45
 * 
 */
public class JourneyAction extends BasicAction {

	private static final long serialVersionUID = -504813428831177920L;
	private static final Logger log = LoggerFactory.getLogger(JourneyAction.class);
	
	private List<Journey> js;
	private List<User> us;
	private int status;
	private String tip;
	private String ujstr;
	
	@Autowired
	JourneyService journeyService;
	@Autowired
	UserService userService;
	@Autowired
	UserJourneyService userJourneyService;
	@Autowired
	TransactionService transactionService;
	
	public String step3journeys(){
		try {
			String logincode = getSessionLogin().getLogincode();
			js = journeyService.getJourneys();
			us = userService.getUsersByLogincode(logincode);
			status = 200;
		} catch (Exception e) {
			e.printStackTrace();
			status = 500;
			tip = Constant.JOURNEY_GET_FAILURE_TIP;
		}
		return SUCCESS;
	}
	
	public String step3insert(){
		try {
			log.debug("ujstr : {}",ujstr);
			String logincode = getSessionLogin().getLogincode();
			List<UserJourney> ujs = new ArrayList<UserJourney>();
			String[] ujarr = ujstr.split("#");
			Map<Long,Integer> parnum = new HashMap<Long, Integer>();
			for(String uj : ujarr){
				String[] temparr = uj.split("_");
				long jid = Long.parseLong(temparr[0]);
				long uid = Long.parseLong(temparr[1]);
				String cuids = temparr[2];
				//成人行程
				UserJourney aujobj = new UserJourney();
				aujobj.setJid(jid);
				aujobj.setUid(uid);
				ujs.add(aujobj);
				if(parnum.get(jid) == null){
					parnum.put(jid,1);
				}else{
					parnum.put(jid, parnum.get(jid)+1);
				}
				//判断是否带小孩
				String[] cuidarr = cuids.split("&");
				for(String cuidstr : cuidarr){
					long cuid = Long.parseLong(cuidstr);
					if(cuid > 0){//带小孩
						parnum.put(jid, parnum.get(jid)+1);
						UserJourney cujobj = new UserJourney();
						cujobj.setJid(jid);
						cujobj.setUid(cuid);
						cujobj.setAuid(uid);
						ujs.add(cujobj);
					}
				}
			}
			log.debug("parnum : {}",parnum);
			log.debug("ujs : {}",ujs);
			tip = transactionService.insertJourney(logincode, ujs, parnum);
		} catch (Exception e) {
			e.printStackTrace();
			status = 500;
		}
		return SUCCESS;
	}
	
	public List<Journey> getJs() {
		return js;
	}
	public void setJs(List<Journey> js) {
		this.js = js;
	}
	public List<User> getUs() {
		return us;
	}
	public void setUs(List<User> us) {
		this.us = us;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getUjstr() {
		return ujstr;
	}
	public void setUjstr(String ujstr) {
		this.ujstr = ujstr;
	}
}
