package com.bk120.sshshop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bk120.sshshop.order.vo.Order;
import com.bk120.sshshop.order.vo.OrderItem;
import com.bk120.sshshop.utils.PageHibernateCallback;

/**
 * 订单模块持久层
 * @author BK120:任旭
 *
 */
public class OrderDao extends HibernateDaoSupport{
	//保存订单的方法
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}
	//查询我的订单的所有订单数
	public Integer findByCountUsid(Integer id) {
		String hql="select count(*) from Order o where o.user.id=?";
		List<Long> list=this.getHibernateTemplate().find(hql,id);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//根据页面查询用户的当前页的数据
	public List<Order> findByPageUsid(Integer id, Integer begin, Integer limit) {
		String hql="from Order o where o.user.id=? order by ordertime desc";
		List<Order> list=this.getHibernateTemplate().execute(
				new PageHibernateCallback<>(hql, new Object[]{id}, begin, limit));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}
	//根据订单id获取订单
	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}
	//修改订单
	public void update(Order currOrder) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(currOrder);
	}
	//后台查询所有订单数量
	public Integer findByCount() {
		String hql="select count(*) from Order";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//后台分页查询所有订单
	public List<Order> findByPage(Integer begin, Integer limit) {
		String hql="from Order order by ordertime desc";
		List<Order> list=this.getHibernateTemplate().execute(
				new PageHibernateCallback<>(hql, null, begin, limit));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}
	//根据订单Id查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql="from OrderItem oi where oi.order.oid=?";
		List<OrderItem> list=this.getHibernateTemplate().find(hql,oid);
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}
	
}
