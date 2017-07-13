package com.bk120.sshshop.cart.vo;
/**
 * 购物车对象
 * @author BK120:任旭
 *
 */

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 存放在Session中的序列化的原因是：当session失效后 理论上的Session中的数据会失去数据  
 * Session失去作用的情况 ：1:超过30分钟
 * 					 2：服务端非正常断开
 * 					3：客户端浏览器断开连接  手动 完全断开
 * 当服务器 的正常断开时 Session会将数据存储到tomat的work文件中的一个本地文件中 进行序列化   所以可能存现 序列化异常   
 * 或则 存入Session的对象实现序列化接口即可
 * @author BK120:任旭
 *
 */
public class Cart implements Serializable{
	//购物项的集合 key:商品id  value:购物项
	private Map<Integer, CartItem> map=new LinkedHashMap<Integer,CartItem>();
	//购物总金额
	private double total;
	/**
	 * 购物车功能：
	 * 1:添加购物项
	 * 2:删除购物想
	 * 3:清空购物车
	 */
	//清空购物车
	public void  clearCart() {
		//总计设为0
		total=0;
		//清空map
		map.clear();
	}
	//移除购物项
	public void  removeCart(Integer pid) {
		//移除购物项
		CartItem item = map.remove(pid);
		total-=item.getSubtotal();
		//修改总金额
	}
	//添加购物车
	public void  addCart(CartItem item) {
		//判断购物项是否已经存在
		//获得商品id
		Integer pid=item.getProduct().getPid();
		if(map.containsKey(pid)){
			//购物项存在
			CartItem oldItem=map.get(pid);
			oldItem.setCount(oldItem.getCount()+item.getCount());
		}else {
			//购物项不存在
			map.put(pid, item);
		}
		total+=item.getSubtotal();
	}
	public double getTotal() {
		return total;
	}
	//提供返回map的value的方法
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
}
