/**   
* @Title: Sms.java
* @Package com.richmobi.amwayfc.domain
* @Description: 
* @author Xuehan.Li
* @date 2014年3月17日 下午5:12:16
* @version V1.0
*/ 
package com.richmobi.amwayfc.domain;

/** 
 * @ClassName: Sms
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月17日 下午5:12:16
 * 
 */
public class Sms extends AbstractDomain {

	private static final long serialVersionUID = -1585270565009271401L;

	private String name;
	private String phone;
	private String password;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
