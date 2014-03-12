package com.richmobi.amwayfc.domain;

import java.util.List;


/** 
* @ClassName: PageResult 
* @Description: 分页记录集
* @author shuangfei.zhang
* @date 2012-3-22 下午12:06:15 
* 
* @param <T> 
* 		分页数据类型
*/
public class PageResult<T> extends AbstractDomain{

	private static final long serialVersionUID = -2845154603770903793L;
	
	/**
	 * 查询记录的开始位置
	 */
	private int start;
	
	/**
	 * 查询记录的结束位置
	 */
	private int end;
	
	/**
	 * 当前页面
	 */
	private int currentPage;
	
	/**
	 * 每页显示记录数
	 */
	private int pageCount;
	
	/**
	 * 总页数
	 */
	private int totalPage;
	
	/**
	 * 记录的总条数
	 */
	private int totalCount;
	
	/**
	 * 查询的数据
	 */
	private List<T> data;
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	
	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		if(totalCount > 0){
			this.totalCount = totalCount;
			this.totalPage = totalCount / this.pageCount;
			if(totalCount % pageCount > 0){
				this.totalPage ++;
			}
		} else {
			this.totalCount = 0;
		}
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public PageResult(int currentPage, int pageCount){
		this.currentPage = currentPage;
		this.pageCount = pageCount;
		this.start = (currentPage - 1) * pageCount;
		this.end = this.start + pageCount;
	}
	
	/**
	 * 判断结果集是否为空
	 * @return
	 * 		true:结果集为空;false:结果集非空
	 */
	public boolean isEmpty(){
		if(this.data == null || this.data.isEmpty()){
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
	
}
