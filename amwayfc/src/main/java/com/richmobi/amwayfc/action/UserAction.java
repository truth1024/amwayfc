package com.richmobi.amwayfc.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.richmobi.amwayfc.domain.Login;
import com.richmobi.amwayfc.domain.User;
import com.richmobi.amwayfc.domain.UserJourney;
import com.richmobi.amwayfc.service.EmailService;
import com.richmobi.amwayfc.service.UserJourneyService;
import com.richmobi.amwayfc.service.UserService;

public class UserAction extends BasicAction {

	private static final long serialVersionUID = 1617158807956202883L;
	private static final Logger log = LoggerFactory.getLogger(UserAction.class);
	
	private int status;
	private String tip;
	private User user;
	private List<User> us;
	private List<User> usFC;
	private List<UserJourney> ujs;
	private int isfirst;
	private String email;
	
	@Autowired
	UserService userService;
	@Autowired
	UserJourneyService userJourneyService;
	@Autowired
	EmailService emailService;
	
	public String step2users(){
		try {
			String logincode = getSessionLogin().getLogincode();
			us = userService.getUsersByLogincode(logincode);
			status = 200;
		} catch (Exception e) {
			e.printStackTrace();
			status = 500;
		}
		return SUCCESS;
	}
	
	public String step3userJourney(){
		try {
			String logincode = getSessionLogin().getLogincode();
			ujs = userJourneyService.getByLogincode(logincode);
			log.debug("step3userJourney : {}",ujs);
			status = 200;
		} catch (Exception e) {
			e.printStackTrace();
			status = 500;
		}
		return SUCCESS;
	}
	
	public String step4users(){
		try {
			Login l = getSessionLogin();
			String logincode = l.getLogincode();
			isfirst = l.getIsfirst();
			us = userService.getUsersByLogincode(logincode);
			status = 200;
		} catch (Exception e) {
			e.printStackTrace();
			status = 500;
		}
		return SUCCESS;
	}
	
	public String step4email(){
		try {
			log.debug("email : {}",email);
			Login l = getSessionLogin();
			String logincode = l.getLogincode();
			us = userService.getUsersByLogincode(logincode);
			emailService.sendEmail(us, email);
			status = 200;
		} catch (Exception e) {
			e.printStackTrace();
			status = 500;
		}
		return SUCCESS;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUs() {
		return us;
	}
	public void setUs(List<User> us) {
		this.us = us;
	}
	public List<UserJourney> getUjs() {
		return ujs;
	}
	public void setUjs(List<UserJourney> ujs) {
		this.ujs = ujs;
	}
	public List<User> getUsFC() {
		return usFC;
	}
	public void setUsFC(List<User> usFC) {
		this.usFC = usFC;
	}
	public int getIsfirst() {
		return isfirst;
	}
	public void setIsfirst(int isfirst) {
		this.isfirst = isfirst;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
