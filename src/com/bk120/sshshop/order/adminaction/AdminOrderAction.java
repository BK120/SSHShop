package com.bk120.sshshop.order.adminaction;

import java.util.List;

import com.bk120.sshshop.order.service.OrderService;
import com.bk120.sshshop.order.vo.Order;
import com.bk120.sshshop.order.vo.OrderItem;
import com.bk120.sshshop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台订单管理Action
 * @author BK120:任旭
 *
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{
	private Order order=new Order();
	@Override
	public Order getModel() {
		return order;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	private OrderService orderService;
	//接受页码
	private Integer page;
	//分页查询所有订单
	public String  findAll() {
		PageBean<Order> pageBean=orderService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	//根据订单id查询订单项
	public String  findOrderItem() {
		System.out.println("order："+order.getOid());
		List<OrderItem> list=orderService.findOrderItem(order.getOid());
		//显示保存数据
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	//修改订单状态
	public String  updateState() {
		//查询订单
		Order cuOrder=orderService.findByOid(order.getOid());
		//发货完成
		cuOrder.setState(3);
		orderService.update(cuOrder);
		return "updateStateSuccess";
	}

}
