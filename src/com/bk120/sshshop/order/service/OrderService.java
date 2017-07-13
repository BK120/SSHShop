package com.bk120.sshshop.order.service;
/**
 * 订单业务层
 * @author BK120:任旭
 *
 */

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bk120.sshshop.order.dao.OrderDao;
import com.bk120.sshshop.order.vo.Order;
import com.bk120.sshshop.order.vo.OrderItem;
import com.bk120.sshshop.utils.PageBean;
@Transactional
public class OrderService {
	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	//保存订单到数据库
	public void save(Order order) {
		orderDao.save(order);
	}
	//我的所有订单的业务层
	public PageBean<Order> findByUsid(Integer id, Integer page) {
		PageBean<Order> pageBean=new PageBean<Order>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示订单数
		Integer limit=5;
		pageBean.setLimit(limit);
		//设置总的记录数
		Integer totalcount=0;
		totalcount=orderDao.findByCountUsid(id);
		pageBean.setTotalCount(totalcount);
		//设置总页数
		Integer totalPage=0;
		if(totalcount%limit==0){
			totalPage=totalcount/limit;
		}else {
			totalPage=totalcount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页显示的数据集合
		Integer begin=(page-1)*limit;
		List<Order> list=orderDao.findByPageUsid(id,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//根据订单id查询一个订单
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}
	//修改订单
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}
	//后台分页查询所有订单
	public PageBean<Order> findByPage(Integer page) {
		PageBean<Order> pageBean=new PageBean<Order>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示订单数
		Integer limit=10;
		pageBean.setLimit(limit);
		//设置总的记录数
		Integer totalcount=0;
		totalcount=orderDao.findByCount();
		pageBean.setTotalCount(totalcount);
		//设置总页数
		Integer totalPage=0;
		if(totalcount%limit==0){
			totalPage=totalcount/limit;
		}else {
			totalPage=totalcount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页显示的数据集合
		Integer begin=(page-1)*limit;
		List<Order> list=orderDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//根据订单id查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}
}
