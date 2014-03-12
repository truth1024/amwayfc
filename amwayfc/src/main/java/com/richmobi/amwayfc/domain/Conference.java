package com.richmobi.amwayfc.domain;

import java.util.Date;

/**
 * 
	 * 创建日期:2012-10-31
	 * Title: 会议
	 * Description：会议详细信息
	 * @author Xuehan.Li
	 * @mender：（文件的修改者，文件创建者之外的人）
	 * @version 1.0
 */
public class Conference extends AbstractDomain {
	private static final long serialVersionUID = 935987576863173142L;
	
	private long id;                   //会议id
	private String conferenceRoom;     //会议厅
	private String floor;              //楼层
	private String conferenceContent;  //会议内容
	private Date conferenceDate;       //会议时间
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getConferenceRoom() {
		return conferenceRoom;
	}
	public void setConferenceRoom(String conferenceRoom) {
		this.conferenceRoom = conferenceRoom;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getConferenceContent() {
		return conferenceContent;
	}
	public void setConferenceContent(String conferenceContent) {
		this.conferenceContent = conferenceContent;
	}
	public Date getConferenceDate() {
		return conferenceDate;
	}
	public void setConferenceDate(Date conferenceDate) {
		this.conferenceDate = conferenceDate;
	}
}
