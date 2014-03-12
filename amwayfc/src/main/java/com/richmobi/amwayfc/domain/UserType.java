package com.richmobi.amwayfc.domain;

/**
 * 
	 * 创建日期:2012-10-31
	 * Title: 用户胸卡类型
	 * Description：存储胸卡名称
	 * @author Xuehan.Li
	 * @mender：（文件的修改者，文件创建者之外的人）
	 * @version 1.0
 */
public class UserType extends AbstractDomain {
	private static final long serialVersionUID = -4909563429571792574L;
	
	private long id;          //胸卡类型id
	private String typeName;  //胸卡名称
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
