package com.bk120.sshshop.index.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.bk120.sshshop.category.service.CategoryService;
import com.bk120.sshshop.category.vo.Category;
import com.bk120.sshshop.product.service.ProductService;
import com.bk120.sshshop.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 用于首页访问的Action
 * @author BK120:任旭
 *
 */
public class IndexAction extends ActionSupport{
	//注入一级分类的Service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//输入商品Service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	/**
	 * 执行访问首页的方法
	 */
	public String  execute() {
		//查询所有一级分类
		List<Category> cList=categoryService.findAll();
		//将一级分类存入Session的范围
		ActionContext.getContext().getSession().put("cList", cList);
		//查询热门商品
		List<Product> hList=productService.findHot();
		//保存入值栈
		ActionContext.getContext().getValueStack().set("hList", hList);
		//查询最新商品 
		List<Product> nList=productService.findNew();
		//保存
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
	
	
}
