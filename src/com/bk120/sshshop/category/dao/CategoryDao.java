package com.bk120.sshshop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bk120.sshshop.category.vo.Category;

/**
 * 一级分类 持久层
 * @author BK120:任旭
 *
 */
public class CategoryDao extends HibernateDaoSupport{
	//查询所有一级分类
	public List<Category> findAll() {
		String hql="from Category";
		List<Category> list=this.getHibernateTemplate().find(hql);
		return list;
	}
	//保存一级分类
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}
	//查询一级分类
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Category.class, cid);
	}
	//删除一级分类
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}
	//后台修改一级分类
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
		
	}

}
