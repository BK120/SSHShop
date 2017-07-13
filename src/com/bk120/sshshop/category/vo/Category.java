package com.bk120.sshshop.category.vo;

import java.util.HashSet;
import java.util.Set;

import com.bk120.sshshop.categorysecond.vo.CategorySecond;

/**
 * 一级分类实体类
 * @author BK120:任旭
 *
 */
public class Category {
	private Integer cid;
	private String cname;
	//存放二级分类集合
	private Set<CategorySecond> categorySeconds=new HashSet<CategorySecond>();
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}

}
