/**   
* @Title: LoginAction.java
* @Package com.richmobi.amwayfc.action
* @Description: 
* @author Xuehan.Li
* @date 2014年3月21日 上午11:02:15
* @version V1.0
*/ 
package com.richmobi.amwayfc.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.richmobi.amwayfc.domain.Login;
import com.richmobi.amwayfc.domain.User;
import com.richmobi.amwayfc.service.EmailService;
import com.richmobi.amwayfc.service.LoginService;
import com.richmobi.amwayfc.util.Constant;
import com.richmobi.amwayfc.util.Utils;

/** 
 * @ClassName: LoginAction
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月21日 上午11:02:15
 * 
 */
public class LoginAction extends BasicAction {

	private static final long serialVersionUID = -2142287039440992162L;
	private static final Logger log = LoggerFactory.getLogger(LoginAction.class);
	
	private String tip;
	private int status;
	private Login login;
	private String password;
	private String confirmPassword;
	private int isFirst;
	private User user;
	
	@Autowired
	EmailService emailService;
	@Autowired
	LoginService loginService;

	public String login(){
		try {
			log.debug("login : {}",login);
			//判断户籍编号或密码是否为空
			if(StringUtils.isBlank(login.getLogincode()) || StringUtils.isBlank(login.getPassword())){
				status = 400;
				tip = Constant.LOGINCODE_PASSWORD_BLANK_TIP;
			}else{
				login.setMd5password(Utils.MD5Encryption(login.getPassword()));
				login = loginService.getLogin(login);
				//判断户籍编号和密码是否错误
				if(login == null){
					status = 400;
					tip = Constant.LOGINCODE_PASSWORD_ERROR_TIP;
				}else{
					isFirst = login.getIsfirst();
					//验证成功将登录信息存入session
					request.getSession().setAttribute(Constant.SESSION_NAME, login);
					status = 200;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 500;
			tip = Constant.LOGIN_FAILURE_TIP;
		} finally{
			login = null;
		}
		return SUCCESS;
	}
	
	public String logout(){
		try {
			request.getSession().removeAttribute(Constant.SESSION_NAME);
			status = 200;
		} catch (Exception e) {
			e.printStackTrace();
			status = 500;
			tip = Constant.LOGOUT_FAILURE_TIP;
		}
		return SUCCESS;
	};

	public String findPassword(){
		log.debug("user : {}",user);
		try {
			emailService.sendEmail(user);
			status = 200;
			tip = Constant.SEND_EMAIL_SUCCESS_TIP;
		} catch (Exception e) {
			e.printStackTrace();
			status = 500;
			tip = Constant.SEND_EMAIL_FAILURE_TIP;
		}
		return SUCCESS;
	}
	
	public String updatePassword(){
		try {
			//判断密码是否相等
			if(!password.equals(confirmPassword)){
				status = 400;
				tip = Constant.PASSWORD_NO_EQUAL_TIP;
			}else{
				login = getSessionLogin();
				log.debug("logincode : {}",login.getLogincode());
				login.setPassword(password);
				login.setMd5password(Utils.MD5Encryption(password));
				loginService.update(login);
				status = 200;
				tip = Constant.PASSWORD_UPDATE_SUCCESS_TIP;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 500;
			tip = Constant.PASSWORD_UPDATE_FAILURE_TIP;
		}finally{
			login = null;
		}
		return SUCCESS;
	}
	
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public int getIsFirst() {
		return isFirst;
	}
	public void setIsFirst(int isFirst) {
		this.isFirst = isFirst;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
