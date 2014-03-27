package com.richmobi.amwayfc.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.richmobi.amwayfc.domain.User;
import com.richmobi.amwayfc.service.UserService;

public class UserAction extends BasicAction {

	private static final long serialVersionUID = 1617158807956202883L;
	private static final Logger log = LoggerFactory.getLogger(UserAction.class);
	
	private int status;
	private String tip;
	private User user;
	private List<User> us;
	
	@Autowired
	UserService userService;

	public String step2users(){
		String logincode = getSessionLogin().getLogincode();
		us = userService.getUsersByLogincode(logincode);
		status = 200;
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
}
