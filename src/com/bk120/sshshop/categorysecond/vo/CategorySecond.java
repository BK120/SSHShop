package com.bk120.sshshop.categorysecond.vo;
/**
 * 二级分类实体类
 * @author BK120:任旭
 *
 */

import java.util.HashSet;
import java.util.Set;

import com.bk120.sshshop.category.vo.Category;
import com.bk120.sshshop.product.vo.Product;

public class CategorySecond {
	private Integer csid;
	private String  csname;
	//一级分类对象
	private Category category=new Category();
	//商品集合
	private Set<Product> products=new HashSet<Product>();
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
