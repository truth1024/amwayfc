/**   
* @Title: EmailServiceImpl.java
* @Package com.richmobi.amwayfc.service.impl
* @Description: 
* @author Xuehan.Li
* @date 2014年3月21日 上午10:20:27
* @version V1.0
*/ 
package com.richmobi.amwayfc.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import richmobi.commons.utils.Configer;

import com.richmobi.amwayfc.domain.User;
import com.richmobi.amwayfc.persistence.UserMapper;
import com.richmobi.amwayfc.service.EmailService;
import com.richmobi.amwayfc.util.EmailUtil;

/** 
 * @ClassName: EmailServiceImpl
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月21日 上午10:20:27
 * 
 */
@Service
public class EmailServiceImpl implements EmailService {

	private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	@Autowired
	UserMapper userMapper;
	@Autowired
	VelocityEngine smsVelocityEngine;
	
	/**
	 * @Title: sendEmail
	 * @Description:发送邮件
	 * @param user
	 * @return
	 * @throws SendFailedException
	 * @author Xuehan.Li
	 * @date 2014年3月21日 上午10:20:28
	 * @see com.richmobi.amwayfc.service.EmailService#sendEmail(com.richmobi.amwayfc.domain.User)
	 */
	@Override
	public boolean sendEmail(User user) throws Exception {
		if(user != null && StringUtils.isNotBlank(user.getEmail())){
			String subject = null;
			String template = null;
			Map<String, String> model = new HashMap<String, String>();
			String name = user.getName();
			log.debug("name : "+name);
			model.put("domain", Configer.get("server.domain"));
			model.put("name", name);
			subject = Configer.get("mail.default.subject");
			template = Configer.get("mail.template.default.name");
			EmailUtil email = new EmailUtil();
			String msg = getContent(template, model);
			email.sendEmail(user.getEmail(), subject, msg);
			return Boolean.TRUE;
		} else {
			throw new IllegalArgumentException("user cannot be null or email cannot be empty!");
		}
	}
	
	
	/** 
	* @Title: getContent
	* @Description: 获取邮件内容
	* @param template
	* @param model
	* @return String
	* @author Xuehan.Li
	* @date 2014年3月21日 下午1:40:04
	*/ 
	private String getContent(String template, Map<String, String> model){
		return  VelocityEngineUtils.mergeTemplateIntoString(smsVelocityEngine, template, "UTF-8", model);
	}

}
