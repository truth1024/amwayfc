package com.richmobi.amwayfc.domain;

import java.util.Date;
import java.util.List;


/** 
* @ClassName: User 
* @Description: 用户信息
* @author shuangfei.zhang
* @date 2012-3-21 下午2:07:40 
* @mender xuehan.li
* @date 2012-7-3
*/
public class User extends AbstractDomain {

	private static final long serialVersionUID = -1138145628576921850L;
	
	/** 
	* @Fields id : 用于ID
	*/ 
	private long id;
	/** 
	* @Fields type : 用户类型
	*/ 
	private long type;
	/** 
	* @Fields registerId : 用户注册id
	*/ 
	private long registerId;
	/** 
	* @Fields code : 用户的编码
	*/ 
	private String code;
	/** 
	* @Fields name : 姓名
	*/ 
	private String name;
	/** 
	* @Fields phone : 电话
	*/ 
	private String phone;
	/** 
	* @Fields email : 邮件地址
	*/ 
	private String email;
	/** 
	* @Fields company : 公司
	*/ 
	private String company;
	/** 
	* @Fields title : 职位
	*/ 
	private String title;
	/** 
	* @Fields companyAdd : 公司地址
	*/ 
	private String companyAdd;
	/** 
	* @Fields note : 备注
	*/ 
	private String note;
	/**
	 * @Fields imageName : 二维码图片名称
	 */
	private String imageName;
	/** 
	* @Fields createDate : 用户创建时间
	*/ 
	private Date createDate;
	/**
	 * @Fields photoName : 照相名称
	 */
	private String photoName;
	/**
	 * @Fields daren : 达人称号
	 */
	private String daren;
	
	private String prize;
	/**
	 * @Fields seatNumber : 座位号
	 */
	private String seatNumber;
	
	private List<CheckinRecd> checkinRecds;
	
	private List<SmsSendRecd> smsSendRecds;
	
	private UserType userType;
	
	private List<Conference> conferences;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public long getRegisterId() {
		return registerId;
	}
	public void setRegisterId(long registerId) {
		this.registerId = registerId;
	}
	public long getType() {
		return type;
	}
	public void setType(long type){
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCompanyAdd() {
		return companyAdd;
	}
	public void setCompanyAdd(String companyAdd) {
		this.companyAdd = companyAdd;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getPrize() {
		return prize;
	}
	public void setPrize(String prize) {
		this.prize = prize;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getDaren() {
		return daren;
	}
	public void setDaren(String daren) {
		this.daren = daren;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public List<CheckinRecd> getCheckinRecds() {
		return checkinRecds;
	}
	public void setCheckinRecds(List<CheckinRecd> checkinRecds) {
		this.checkinRecds = checkinRecds;
	}
	public List<SmsSendRecd> getSmsSendRecds() {
		return smsSendRecds;
	}
	public void setSmsSendRecds(List<SmsSendRecd> smsSendRecds) {
		this.smsSendRecds = smsSendRecds;
	}
	
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public List<Conference> getConferences() {
		return conferences;
	}
	public void setConferences(List<Conference> conferences) {
		this.conferences = conferences;
	}
	public User() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((checkinRecds == null) ? 0 : checkinRecds.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((companyAdd == null) ? 0 : companyAdd.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((seatNumber == null) ? 0 : seatNumber.hashCode());
		result = prime * result + ((imageName == null) ? 0 : imageName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((smsSendRecds == null) ? 0 : smsSendRecds.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + (int) (type ^ (type >>> 32));;
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
		User other = (User) obj;
		if (checkinRecds == null) {
			if (other.checkinRecds != null)
				return false;
		} else if (!checkinRecds.equals(other.checkinRecds))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (companyAdd == null) {
			if (other.companyAdd != null)
				return false;
		} else if (!companyAdd.equals(other.companyAdd))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (seatNumber != other.seatNumber)
			return false;
		if (imageName == null) {
			if (other.imageName != null)
				return false;
		} else if (!imageName.equals(other.imageName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (smsSendRecds == null) {
			if (other.smsSendRecds != null)
				return false;
		} else if (!smsSendRecds.equals(other.smsSendRecds))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	
	
}
