package com.bk120.sshshop.utils;
/**
 * 商品分页类的封装
 * @author BK120:任旭
 *
 */

import java.util.List;

public class PageBean<T>
{
	//当前所在的页码数
	private int page;
	private int totalCount;//总的记录数
	private int totalPage;//总的页数
	private int limit;//每页显示的商品数
	private List<T> list;//总的商品集合
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}
