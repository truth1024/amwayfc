package com.richmobi.amwayfc.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/** 
* @ClassName: AbstractDomain 
* @Description: 实体抽象类
* @author shuangfei.zhang
* @date 2012-3-21 下午2:11:12 
*  
*/
public class AbstractDomain implements Serializable{

	private static final long serialVersionUID = 8257832289817409654L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
