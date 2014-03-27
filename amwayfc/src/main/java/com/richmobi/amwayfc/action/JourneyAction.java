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
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.richmobi.amwayfc.domain.Journey;
import com.richmobi.amwayfc.domain.User;
import com.richmobi.amwayfc.domain.UserJourney;
import com.richmobi.amwayfc.service.JourneyService;
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
		log.debug("ujstr : {}",ujstr);
		String logincode = getSessionLogin().getLogincode();
		userJourneyService.deleteByLogincode(logincode);
		List<UserJourney> ujs = new ArrayList<UserJourney>();
		userJourneyService.batchInsert(ujs);
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
