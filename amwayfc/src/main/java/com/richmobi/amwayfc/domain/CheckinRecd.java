package com.richmobi.amwayfc.domain;

import java.util.Date;

/** 
* @ClassName: CheckinRecd 
* @Description: 签到记录
* @author shuangfei.zhang
* @date 2012-3-21 下午3:55:25 
*  
*/
public class CheckinRecd extends AbstractDomain {

	private static final long serialVersionUID = -3826592452433718632L;
	
	/** 
	* @Fields id : 记录ID
	*/ 
	private long id;
	/** 
	* @Fields userId : 用户ID
	*/ 
	private long userId;
	/** 
	* @Fields checkinWary : 签到方式
	*/ 
	private int checkinWary;
	/** 
	* @Fields checkinDate : 签到日期
	*/ 
	private Date checkinDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getCheckinWary() {
		return checkinWary;
	}
	public void setCheckinWary(int checkinWary) {
		this.checkinWary = checkinWary;
	}
	public Date getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}
	
	
	public CheckinRecd() {
		super();
	}
	
	public CheckinRecd(long userId, int checkinWary) {
		super();
		this.userId = userId;
		this.checkinWary = checkinWary;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + checkinWary;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheckinRecd other = (CheckinRecd) obj;
		if (checkinWary != other.checkinWary)
			return false;
		if (id != other.id)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
}
