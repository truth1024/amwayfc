package com.richmobi.amwayfc.persistence;

import java.util.List;

import com.richmobi.amwayfc.domain.SmsSendRecd;

public interface SmsSendRecdMapper {
	
	public void insert(SmsSendRecd smsSendRecd);
	
	public SmsSendRecd getById(long id);
	
	public List getByUserId(long userId);
	
	
}
