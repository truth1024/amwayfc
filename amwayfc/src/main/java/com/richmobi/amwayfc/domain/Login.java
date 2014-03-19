/**   
* @Title: Login.java
* @Package com.richmobi.amwayfc.domain
* @Description: 
* @author Xuehan.Li
* @date 2014年3月17日 下午4:08:56
* @version V1.0
*/ 
package com.richmobi.amwayfc.domain;

import java.util.List;

/** 
 * @ClassName: Login
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月17日 下午4:08:56
 * 
 */
public class Login extends AbstractDomain {

	private static final long serialVersionUID = 8461159359972163601L;

	private long id;
	private String logincode;
	private String password;
	private String md5password;
	private int isfirst;
	
	private List<User> users;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLogincode() {
		return logincode;
	}
	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMd5password() {
		return md5password;
	}
	public void setMd5password(String md5password) {
		this.md5password = md5password;
	}
	public int getIsfirst() {
		return isfirst;
	}
	public void setIsfirst(int isfirst) {
		this.isfirst = isfirst;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
