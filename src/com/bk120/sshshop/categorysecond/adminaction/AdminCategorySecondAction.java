package com.bk120.sshshop.categorysecond.adminaction;

import java.util.List;

import com.bk120.sshshop.category.service.CategoryService;
import com.bk120.sshshop.category.vo.Category;
import com.bk120.sshshop.categorysecond.service.CategorySecondService;
import com.bk120.sshshop.categorysecond.vo.CategorySecond;
import com.bk120.sshshop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台二级分类管理Action
 * @author BK120:任旭
 *
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	private CategorySecond categorySecond=new CategorySecond();
	@Override
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	private CategorySecondService categorySecondService;
	//接受页码
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	//查询二级分类
	public String  findAll() {
		PageBean<CategorySecond> pageBean=categorySecondService.findByPage(page);
		//保存到值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	//注入一级分类
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//跳转到二级分类添加页面
	public String  addPage() {
		//查询所有一级分类
		List<Category> cList = categoryService.findAll();
		//把数据显示到页面的下拉列表中
		ActionContext.getContext().getValueStack().set("cList", cList);
		//页面跳转
		return "addPageSuccess";
	}
	//保存二级分类
	public String  save() {
			/////此处操作室容易出现CategorySecond中的category为空  接收不到数据  
			//////解决  去CategorySecond中手动完成 一级分类的构造器 
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	//删除二级分类
	public String  delete() {
		//如果级联删除   先查询 在 删除  并且 配置 一级分类的 cascade
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	//编辑二级分类的方法
	public String  edit() {
		//查询二级分类对象
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		//查询所有一级分类
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	//修改二级分类的方法
	public String  update() {
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}

}
