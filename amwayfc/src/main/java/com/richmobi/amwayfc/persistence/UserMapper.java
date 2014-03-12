package com.richmobi.amwayfc.persistence;

import java.util.List;
import java.util.Map;

import com.richmobi.amwayfc.domain.User;

/** 
* @ClassName: UserMapper 
* @Description: 提供用户信息的CRUD
* @author shuangfei.zhang
* @date 2012-3-21 下午2:32:58 
*  
*/
public interface UserMapper {
	/** 
	* @Title: insert 
	* @Description: 插入一条用户信息
	* @param user  用户信息
	* @return 
	* @throws 
	*/
	public void insert(User user);
	/** 
	* @Title: batchInsertUser 
	* @Description: 批量插入用户信息
	* @param users  用户信息列表
	* @return 
	* @throws 
	*/
	public void batchInsertUser(List<User> users);
	/**
	* @Title: delete
	* @Description: 根据用户ID删除一个用户
	* @param id
	* @throws
	*/
	public void delete(long id);
	
	/**
	* @Title: updateUser
	* @Description: 更新用户信息
	* @param user
	* 		用户信息
	* @throws
	*/
	public void update(User user);
	/**
	 * 
		 * 功能: 获取拍照数目
		 * 作者: Xuehan.Li
		 * 创建日期:2012-7-16
		 * 修改者: mender
	 */
	public int getPhotoDateNum();
	
	/**
	 * 
		 * 功能: 添加拍照时间
		 * 作者: Xuehan.Li
		 * 创建日期:2012-7-3
		 * @param user
	 */
	public void updatePhotoDate(User user);
	/**
	 * 
		 * 功能: 添加照片名称
		 * 作者: Xuehan.Li
		 * 创建日期:2012-7-3
		 * @param user
	 */
	public void updatePhotoName(User user);
	/**
	 * 
		 * 功能: 添加达人称号
		 * 作者: Xuehan.Li
		 * 创建日期:2012-7-3
		 * @param user
	 */
	public void updateDaren(User user);
	/**
	 * 
		 * 功能: 添加领奖
		 * 作者: Xuehan.Li
		 * 创建日期:2012-7-16
		 * @param user
	 */
	public void updatePrize(User user);
	/**
	* @Title: getById
	* @Description: 用户ID
	* @param id
	* 		用户ID
	* @return
	* 		返回用户信息
	* @throws
	*/
	public User getById(long id);
	
	/**
	* @Title: getByCode
	* @Description: 根据用户编码查询用户信息
	* @param code 用户编码
	* @return
	* 		返回用户信息
	* @throws
	*/
	public User getByCode(String code);
	
	/**
	* @Title: getByRegisterId
	* @Description: 根据用户注册id查询用户信息
	* @param registerId 用户注册id
	* @return
	* 		返回用户信息list
	* @throws
	*/
	public List<User> getByRegisterId(long registerId);
	
	/**
	* @Title: getByPhone
	* @Description: 根据电话获取用户
	* @param phone
	* @return
	* @throws
	*/
	public List<User> getByPhone(String phone);
	
	/**
	* @Title: getByConditions
	* @Description: 多条件组合查询用户信息
	* @param conditions 
	* 		类型为HashMap，key:字段名字，value:条件值，部分字段为模糊查询,包含分页信息(start：开始位置，limit：条数)
	* @return
	* 		返回分页数据
	* @throws
	*/
	@SuppressWarnings("rawtypes")
	public List<User> getByConditionsWithPage(Map conditions);
	
	/**
	* @Title: getAllByConditions
	* @Description: 多条件组合查询用户信息
	* @param conditions 
	* 		类型为HashMap，key:字段名字，value:条件值，部分字段为模糊查询
	* @return List<User>
	* @throws
	*/
	@SuppressWarnings("rawtypes")
	public List<User> getAllByConditions(Map conditions);
	
	/**
	* @Title: getAllUser
	* @Description: 查询所以用户
	* @return
	* @throws
	*/
	public List<User> getAllUser();
	
	/**
	* @Title: getAllUserWithPage
	* @Description: 分页查询所有用户信息
	* @return
	* @throws
	*/
	@SuppressWarnings("rawtypes")
	public List<User> getAllUserWithPage(Map startAndLimit);
	
	/**
	* @Title: getUsersWithoutCodeAndImgName
	* @Description: 获取没有生成编码或二维码图片的用户
	* @return
	* @throws
	*/
	public List<User> getUsersWithoutCodeOrImgName();
	
	@SuppressWarnings("rawtypes")
	public int getCountByConditions(Map conditions);
	
	/**
	 * 
		 * 功能:按code排序
		 * 作者: xingfu.he
		 * 创建日期:2012-7-4
		 * 修改者: mender
		 * 修改日期: modifydate
		 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<User> orderByCodeOrDate(Map conditions);
}
