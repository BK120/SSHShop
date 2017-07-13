package com.bk120.sshshop.product.service;
/**
 * 商品业务层
 * @author BK120:任旭
 *
 */

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bk120.sshshop.product.dao.ProductDao;
import com.bk120.sshshop.product.vo.Product;
import com.bk120.sshshop.utils.PageBean;
@Transactional
public class ProductService {
	//注入
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	//查询首页热门商品
	public List<Product> findHot() {
		return productDao.findHot();
	}
	//查询最新商品
	public List<Product> findNew() {
		// TODO Auto-generated method stub
		return productDao.findNew();
	}
	//通过商品id获取商品信息
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}
	//根据以及分类的id且代用分页的查询
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		//进行分页类对象的封装和操作
		PageBean<Product> pageBean=new PageBean<Product>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示记录数
		int limit=8;
		pageBean.setLimit(limit);
		//设置总的记录数
		int totalCount=productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		if(totalCount % limit ==0){
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		//totalPage=(int) Math.ceil(totalCount/limit);
		pageBean.setTotalPage(totalPage);
		//每页显示的数据集合
		//每页开始部分
		int begin=(page-1)*limit;
		List<Product> list=productDao.findByPageCid(cid,begin,limit);//每一页的数据集合
		pageBean.setList(list);
		return pageBean;
	}
	//根据二级分类id查询商品 带分页
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		//进行分页类对象的封装和操作
				PageBean<Product> pageBean=new PageBean<Product>();
				//设置当前页数
				pageBean.setPage(page);
				//设置每页显示记录数
				int limit=8;
				pageBean.setLimit(limit);
				//设置总的记录数
				int totalCount=productDao.findCountCsid(csid);
				pageBean.setTotalCount(totalCount);
				//设置总页数
				int totalPage=0;
				if(totalCount % limit ==0){
					totalPage=totalCount/limit;
				}else {
					totalPage=totalCount/limit+1;
				}
				//totalPage=(int) Math.ceil(totalCount/limit);
				pageBean.setTotalPage(totalPage);
				//每页显示的数据集合
				//每页开始部分
				int begin=(page-1)*limit;
				List<Product> list=productDao.findByPageCsid(csid,begin,limit);//每一页的数据集合
				pageBean.setList(list);
				return pageBean;
	}
	//查询商品带分页
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean=new PageBean<Product>();
		//设置数据
		pageBean.setPage(page);
		int limit=10;
		pageBean.setLimit(limit);
		//总的记录数
		int totalCount=productDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总的页数
		int totalPage=0;
		if(totalCount % limit ==0){
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<Product> list=productDao.findByPage(begin,limit);//每一页的数据集合
		pageBean.setList(list);
		return pageBean;
	}
	//保存商品
	public void save(Product product) {
		productDao.save(product);
		
	}
	//删除图片
	public void delete(Product product) {
		productDao.delete(product);
		
	}
	//修改商品
	public void update(Product product) {
		productDao.update(product);
		
	}
}
