package com.richmobi.amwayfc.domain;

import java.util.Date;

public class SmsSendRecd extends AbstractDomain{

	private static final long serialVersionUID = 580389850109056957L;

	private long id;
	private long userId;
	private Date sendDate;
	
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
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		SmsSendRecd other = (SmsSendRecd) obj;
		if (id != other.id)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	public SmsSendRecd(long userId) {
		super();
		this.userId = userId;
	}
	public SmsSendRecd() {
		super();
	}
	
	
	
	
}
