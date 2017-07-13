package com.bk120.sshshop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.bk120.sshshop.cart.vo.Cart;
import com.bk120.sshshop.cart.vo.CartItem;
import com.bk120.sshshop.product.service.ProductService;
import com.bk120.sshshop.product.vo.Product;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 购物车的Action
 * @author BK120:任旭
 *
 */
public class CartAction extends ActionSupport{
	//接受商品id
	private Integer pid;
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	//注入商品Service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//接受商品数量
	private Integer count;
	//将商品添加到购物车
	public String  addCart() {
		//封装CartItem
		CartItem cartItem=new CartItem();
		cartItem.setCount(count);
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		//将购物项添加到购物车  购物车存入Session 购物车不存储于数据库 与用户登录与否无关
		Cart cart=getCart();
		cart.addCart(cartItem);
		return "addCart";
	}
	//从Session中获得购物车
	private Cart getCart() {
		Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null){
			cart =new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	//清空购物车执行方法
	public String clearCart() {
		Cart cart=getCart();
		cart.clearCart();
		//返回购物车界面  显示去掉的购物项
		return "addCart";
	}
	//从购物车中移除购物项
	public String  removeCart() {
		Cart cart=getCart();
		cart.removeCart(pid);
		return "addCart";
	}
	//由菜单跳转到购物车的方法 
	public String  myCart() {
		return "addCart";
	}
}
