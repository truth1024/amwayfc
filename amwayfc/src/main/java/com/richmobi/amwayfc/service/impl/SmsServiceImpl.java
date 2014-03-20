/**   
* @Title: SmsServiceImpl.java
* @Package com.richmobi.amwayfc.service.impl
* @Description: 
* @author Xuehan.Li
* @date 2014年3月20日 下午4:30:32
* @version V1.0
*/ 
package com.richmobi.amwayfc.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import richmobi.commons.utils.Configer;

import com.richmobi.amwayfc.domain.User;
import com.richmobi.amwayfc.service.SmsService;
import com.richmobi.amwayfc.service.UserService;
import com.richmobi.sms.domain.SMS;
import com.richmobi.sms.domain.SMSParm;
import com.richmobi.sms.service.SendSmsService;

/** 
 * @ClassName: SmsServiceImpl
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月20日 下午4:30:32
 * 
 */
@Service
public class SmsServiceImpl implements SmsService {

	private final Logger log = LoggerFactory.getLogger(SmsService.class);
	
	@Autowired
	SendSmsService sendSmsService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	VelocityEngine smsVelocityEngine;
	
	/**
	 * @Title: sendSMS
	 * @Description:
	 * @param user
	 * @param msg
	 * @return
	 * @author Xuehan.Li
	 * @date 2014年3月20日 下午4:30:32
	 * @see com.richmobi.amwayfc.service.SmsService#sendSMS(com.richmobi.amwayfc.domain.User, java.lang.String)
	 */
	@Override
	public boolean sendSMS(User user, String msg) {
		String phone = user.getPhone();
		if(StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(msg)){
			SMS sms=new SMS();
			SMSParm smsParm=new SMSParm();
			smsParm.setTo_phone(StringUtils.trim(phone));
			smsParm.setMsg(msg);
			smsParm.setApp_key(Configer.get("sms.app.key"));
			try {
				log.info("start send sms to phone [{}] ...", phone);
				log.debug("phone:" + phone + " msg:" + msg);
				 sms=sendSmsService.sendSmsMsg(smsParm);
				 log.debug("send status:{}",sms.getResultCode());
				 if(sms.getResultCode() == 0){
						log.info("send sms to phone [{}] success!",phone);
						return Boolean.TRUE;
					} else {
						log.error("send sms to phone [{}] failed!",phone);
						return Boolean.FALSE;
					}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("send sms to phone [{}] failed!",phone);
				log.error(e.getMessage(),e);
				return Boolean.FALSE;
			}
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * @Title: sendNotificationSMS
	 * @Description:
	 * @param user
	 * @return
	 * @author Xuehan.Li
	 * @date 2014年3月20日 下午4:30:32
	 * @see com.richmobi.amwayfc.service.SmsService#sendNotificationSMS(com.richmobi.amwayfc.domain.User)
	 */
	@Override
	public boolean sendNotificationSMS(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @Title: sendSuccessSMS
	 * @Description:
	 * @param user
	 * @return
	 * @author Xuehan.Li
	 * @date 2014年3月20日 下午4:30:32
	 * @see com.richmobi.amwayfc.service.SmsService#sendSuccessSMS(com.richmobi.amwayfc.domain.User)
	 */
	@Override
	public boolean sendSuccessSMS(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
