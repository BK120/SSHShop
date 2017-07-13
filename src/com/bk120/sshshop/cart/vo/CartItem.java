package com.bk120.sshshop.cart.vo;
/**
 * 购物车里面的购物项对象
 * @author BK120:任旭
 *
 */

import com.bk120.sshshop.product.vo.Product;

public class CartItem {
	private Product product;//实物
	private int count;//购物数量
	private double subtotal;//购买某种商品的小计
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	//小计 计算而得出
	public double getSubtotal() {
		return count*product.getShop_price();
	}

}
