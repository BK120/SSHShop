package com.bk120.sshshop.product.action;

import java.util.List;

import com.bk120.sshshop.category.service.CategoryService;
import com.bk120.sshshop.category.vo.Category;
import com.bk120.sshshop.product.service.ProductService;
import com.bk120.sshshop.product.vo.Product;
import com.bk120.sshshop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 商品的Action；类
 * @author BK120:任旭
 *
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product=new Product();
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	//注入商品的Service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//接受以及分类的cid
	private Integer cid;
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getCid() {
		return this.cid;
	}
	//接受当前的页数
	private int page;
	public void setPage(int page) {
		this.page = page;
	}
	//注入一级分类Service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//根据商品id查询商品详情 执行方法
	public String findByPid(){
		product=productService.findByPid(product.getPid());
		return "findByPid";
	}
	//根据一级分类的id查询商品 
	public String  findByCid() {
		//查询所有一级分类
		//List<Category> cList = categoryService.findAll();
		//从Session中获取一级分类
		//带有当前页码 和一级分类id的查询
		PageBean<Product> pageBean=productService.findByPageCid(cid,page);
		//存入值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	//接受二级分类的id
	private Integer csid;
	//根据二级分类的id查询商品
	public String  findByCsid() {
		//带分页查询
		PageBean<Product> pageBean=productService.findByPageCsid(csid,page);
		//存入值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
	
	
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	
}
