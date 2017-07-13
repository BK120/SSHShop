package com.bk120.sshshop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bk120.sshshop.product.vo.Product;
import com.bk120.sshshop.utils.PageHibernateCallback;

import oracle.net.aso.p;

/**
 * 商品持久层
 * 
 * @author BK120:任旭
 *
 */
public class ProductDao extends HibernateDaoSupport {
	// 首页热门商品查询 带分页查询 查询热门的10个商品
	public List<Product> findHot() {
		// 使用离线条件查询
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Product.class);
		// 查询人们
		detachedCriteria.add(Restrictions.eq("is_hot", 1));
		// 按插入时间倒叙排序 最新插入的且热门排在前面
		detachedCriteria.addOrder(Order.desc("pdate"));
		// 获取前十个
		List<Product> list = this.getHibernateTemplate().findByCriteria(detachedCriteria, 0, 10);
		return list;
	}

	// 首页最新商品查询 10个
	public List<Product> findNew() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 按时间排序
		criteria.addOrder(Order.desc("pdate"));
		// 获取前十个
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}
	//通过商品id获取商品对象
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}
	//查询一级分类查询商品的总个数
	public int findCountCid(Integer cid) {
		String hql="select count(*) from Product p where p.categorySecond.category.cid=?";
		List<Long> list = this.getHibernateTemplate().find(hql,cid);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//根据一级分类的id 和页码查询当前页的所有商品
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		//select p.* from webshop_category c,webshop_categorysecond cs,webshop_product p 
		//where p.csid = cs.csid and cs.cid = c.cid and c.cid=3;
		String hql="select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,
				new Object[]{cid}, begin, limit));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}
	//查询二级分类查询商品的总个数
	public int findCountCsid(Integer csid) {
		String hql="select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list = this.getHibernateTemplate().find(hql,csid);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//根据二级分类的id 和页码查询当前页的所有商品
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql="select p from Product p join p.categorySecond cs where cs.csid=?";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,
				new Object[]{csid}, begin, limit));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}
	//后台查询所有商品数
	public int findCount() {
		String hql="select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//后台获取所有商品页
	public List<Product> findByPage(int begin, int limit) {
		String hql="from Product order by pdate desc";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,
				null, begin, limit));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}
	//保存商品
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}
	//删除图片
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}
	//修改商品
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
		
	}

}
