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
	
	private long id;
	private String logincode;
	private String name;
	private String phone;
	private String sex;
	private int isadult;
	private int isjoin;
	private String areacode;
	private String areaname;
	private String province;
	private String city;
	private String store;
	private String nature;
	private String relation;
	private Date birthdate;
	private int age;
	private String visa;
	private String airticket;
	private String diet;
	private int istake;
	
	private List<Journey> js;
	private List<Long> cuids;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLogincode() {
		return logincode;
	}
	public void setLogincode(String logincode) {
		this.logincode = logincode;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getIsadult() {
		return isadult;
	}
	public void setIsadult(int isadult) {
		this.isadult = isadult;
	}
	public int getIsjoin() {
		return isjoin;
	}
	public void setIsjoin(int isjoin) {
		this.isjoin = isjoin;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getVisa() {
		return visa;
	}
	public void setVisa(String visa) {
		this.visa = visa;
	}
	public String getAirticket() {
		return airticket;
	}
	public void setAirticket(String airticket) {
		this.airticket = airticket;
	}
	public String getDiet() {
		return diet;
	}
	public void setDiet(String diet) {
		this.diet = diet;
	}
	public int getIstake() {
		return istake;
	}
	public void setIstake(int istake) {
		this.istake = istake;
	}
	public List<Journey> getJs() {
		return js;
	}
	public void setJs(List<Journey> js) {
		this.js = js;
	}
	public List<Long> getCuids() {
		return cuids;
	}
	public void setCuids(List<Long> cuids) {
		this.cuids = cuids;
	}
}
