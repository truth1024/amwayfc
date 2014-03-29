/**   
* @Title: UserJourney.java
* @Package com.richmobi.amwayfc.domain
* @Description: 
* @author Xuehan.Li
* @date 2014年3月18日 下午4:17:24
* @version V1.0
*/ 
package com.richmobi.amwayfc.domain;

/** 
 * @ClassName: UserJourney
 * @Description: 用户行程关联实体
 * @author Xuehan.Li
 * @date 2014年3月18日 下午4:17:24
 * 
 */
public class UserJourney extends AbstractDomain{

	private static final long serialVersionUID = 7817754170120667931L;
	
	private long id;
	private long uid;
	private long jid;
	private long auid;
	
	private int parnum;			//参加数
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public long getJid() {
		return jid;
	}
	public void setJid(long jid) {
		this.jid = jid;
	}
	public long getAuid() {
		return auid;
	}
	public void setAuid(long auid) {
		this.auid = auid;
	}
	public int getParnum() {
		return parnum;
	}
	public void setParnum(int parnum) {
		this.parnum = parnum;
	}
}
