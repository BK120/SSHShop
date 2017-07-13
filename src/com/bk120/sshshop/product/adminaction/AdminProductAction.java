package com.bk120.sshshop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.bk120.sshshop.categorysecond.service.CategorySecondService;
import com.bk120.sshshop.categorysecond.vo.CategorySecond;
import com.bk120.sshshop.product.service.ProductService;
import com.bk120.sshshop.product.vo.Product;
import com.bk120.sshshop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台商品管理Action
 * 
 * @author BK120:任旭
 *
 */
public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {
	private Product product = new Product();

	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// 注入
	private ProductService productService;
	// 接受page参数
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	// 查询所有商品
	public String findAll() {
		// 调用Service完成查询
		PageBean<Product> pageBean = productService.findByPage(page);
		// 存入数据
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	// 注入二级业务层
	private CategorySecondService categorySecondService;

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	// 跳转到添加商品页面
	public String addPage() {
		// 查询所有二级分类集合
		List<CategorySecond> csList = categorySecondService.findAll();
		// 保存数据
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPageSuccess";
	}

	//////// 文件上传必须参数 注意三个参数名字 必须与表单名字一样 upload
	private File upload;// 上传的文件
	private String uploadFileName;// 文件上传名称
	private String uploadContextType;// 文件格式

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	// 保存商品的方法
	public String save() throws IOException {
		// 初始化数据
		product.setPdate(new Date());
		if (upload != null) {
			// 文件上传到服务器
			// 获得文件上传绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			File diskFile = new File(realPath + "//" + uploadFileName);
			// 上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}

	// 删除商品的方法
	public String delete() {
		product = productService.findByPid(product.getPid());
		productService.delete(product);
		// 删除服务器上的上传图片
		String path = product.getImage();
		if (path != null) {
			// 删除
			String realPath = ServletActionContext.getServletContext().getRealPath("/" + path);
			File file = new File(realPath);
			// 服务器上删除
			file.delete();
		}

		return "deleteSuccess";
	}

	// 跳转到编辑商品页面
	public String edit() {
		// 查询商品
		product = productService.findByPid(product.getPid());
		// 查询所有二级分类集合
		List<CategorySecond> csList = categorySecondService.findAll();
		// 数据保存到页面
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}

	// 修改商品的方法
	public String  update() throws IOException {
		//初始化数据
		product.setPdate(new Date());
		if(upload!=null){
			//先删除原图片
			String path=product.getImage();
			String realPath = ServletActionContext.getServletContext().getRealPath("/" + path);
			File file = new File(realPath);
			if (file.exists()) {
				file.delete();
			}
			//新文件文件上传到服务器  
			//获得文件上传绝对路径
			String realPath1=ServletActionContext.getServletContext().getRealPath("/products");
			File diskFile=new File(realPath1+"//"+uploadFileName);
			//上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/"+uploadFileName);
		}
		//修改商品数据到数据库
		CategorySecond categorySecond=categorySecondService.findByCsid(product.getCategorySecond().getCsid());
		product.setCategorySecond(categorySecond);
		productService.update(product);
		return "updateSuccess";
	}

}
