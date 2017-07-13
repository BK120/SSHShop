package com.bk120.sshshop.category.adminaction;

import java.util.List;

import com.bk120.sshshop.category.service.CategoryService;
import com.bk120.sshshop.category.vo.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台一级分类管理Action
 * 
 * @author BK120:任旭
 *
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {
	private Category category = new Category();

	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}

	// 注入一级分类Service
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// 后台执行查询所有一级分类的方法
	public String findAll() {
		// 查询所有一级分类
		List<Category> cList = categoryService.findAll();
		// 保存数据
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}

	// 保存一级份分类方法
	public String save() {

		categoryService.save(category);
		return "saveSuccess";
	}

	// 删除一级分类
	public String delete() {
		//先查询  再删除
		category=categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		return "deleteSuccess";
	}
	
	// 后台 修改一级分类  跳转 
	public String  edit() {
		category=categoryService.findByCid(category.getCid());
		return "eidtSuccess";
	}
	//后台修改一级分类 
	public String  update() {
		categoryService.update(category);
		return "updateSuccess";
	}

}
