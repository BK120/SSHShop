package com.bk120.sshshop.category.service;
/**
 * 以及分类业务层
 * @author BK120:任旭
 *
 */

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bk120.sshshop.category.dao.CategoryDao;
import com.bk120.sshshop.category.vo.Category;
@Transactional
public class CategoryService {
	//注入categoryDao
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	//查询所有以及分类
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}
	//保存一级分类的方法
	public void save(Category category) {
		categoryDao.save(category);
	}
	//查询一级分类
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return categoryDao.findByCid(cid);
	}
	//业务层删除一级分类
	public void delete(Category category) {
		categoryDao.delete(category);
		
	}
	//后天修改一级分类
	public void update(Category category) {
		categoryDao.update(category);
	}
}
