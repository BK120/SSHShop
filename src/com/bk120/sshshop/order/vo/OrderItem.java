package com.bk120.sshshop.order.vo;
/**
 * 订单项实体类
 * @author BK120:任旭
 *
 */

import com.bk120.sshshop.product.vo.Product;

public class OrderItem {
	private Integer itemid;
	private Integer count;
	private Double subtotal;
	//两个外键 关联到对象
	private Order order;
	private Product product;
	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

}
