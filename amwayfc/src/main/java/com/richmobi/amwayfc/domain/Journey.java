/**   
* @Title: Journey.java
* @Package com.richmobi.amwayfc.domain
* @Description: 
* @author Xuehan.Li
* @date 2014年3月17日 下午4:06:10
* @version V1.0
*/ 
package com.richmobi.amwayfc.domain;

import java.util.Date;

/** 
 * @ClassName: Journey
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月17日 下午4:06:10
 * 
 */
public class Journey extends AbstractDomain {

	private static final long serialVersionUID = -7303521632328591922L;

	private long id;
	private String title;
	private String briefinfo;
	private Date start;
	private Date end;
	private String infopath;
	private int num;
	private int istake;
	private int remainnum;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBriefinfo() {
		return briefinfo;
	}
	public void setBriefinfo(String briefinfo) {
		this.briefinfo = briefinfo;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getInfopath() {
		return infopath;
	}
	public void setInfopath(String infopath) {
		this.infopath = infopath;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getIstake() {
		return istake;
	}
	public void setIstake(int istake) {
		this.istake = istake;
	}
	public int getRemainnum() {
		return remainnum;
	}
	public void setRemainnum(int remainnum) {
		this.remainnum = remainnum;
	}
}
