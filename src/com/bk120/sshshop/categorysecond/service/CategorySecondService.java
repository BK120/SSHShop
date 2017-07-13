package com.bk120.sshshop.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bk120.sshshop.categorysecond.dao.CategorySecondDao;
import com.bk120.sshshop.categorysecond.vo.CategorySecond;
import com.bk120.sshshop.utils.PageBean;

/**
 * 二级分类管理业务层
 * @author BK120:任旭
 *
 */
@Transactional
public class CategorySecondService {
	private CategorySecondDao categorySecondDao;


	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	//业务层分页查询二级分类
	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean=new PageBean<>();
		//设置
		pageBean.setPage(page);
		//设置每页显示记录数  14
		int limit=14;
		pageBean.setLimit(limit);
		//设置总的记录数
		int totalCount=categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总的页数
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页显示数据集合
		int begin=(page-1)*limit;
		List<CategorySecond> list=categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//保存二级分类方法
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
		
	}
	//查询一个二级分类
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}
	//删除一个二级分类
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
		
	}
	//修改二级分类
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
		
	}
	//查询所有二级分类
	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}

}
