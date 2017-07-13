package com.bk120.sshshop.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.ietf.jgss.Oid;

import com.bk120.sshshop.cart.vo.Cart;
import com.bk120.sshshop.cart.vo.CartItem;
import com.bk120.sshshop.order.service.OrderService;
import com.bk120.sshshop.order.vo.Order;
import com.bk120.sshshop.order.vo.OrderItem;
import com.bk120.sshshop.user.vo.User;
import com.bk120.sshshop.utils.PageBean;
import com.bk120.sshshop.utils.PaymentUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 订单模块的实体类对象想
 * @author BK120:任旭
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	private Order order =new Order();
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	//生成订单的方法  跳转
	public String  save() {
		//将数据保存到数据库 
		order.setOrdertime(new Date());
		order.setState(1);//设置订单状态 1：未付款 2：已经付款 未发货  3：已经发货  未确认  4：交易完成
		//设置总计  购物车的数据
		Cart  cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null){
			this.addActionError("您还没有购物！请先去购物！");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		//遍历的获取购物车中的所有购物项  保存到订单项中
		for (CartItem item : cart.getCartItems()) {
			OrderItem orderItem=new OrderItem();
			orderItem.setCount(item.getCount());
			orderItem.setOrder(order);
			orderItem.setProduct(item.getProduct());
			orderItem.setSubtotal(item.getSubtotal());
			//将所有的订单项保存到订单中
			order.getOrderItems().add(orderItem);
		}
		User user=(User) ServletActionContext.getRequest().getSession().
				getAttribute("existUser");
		if(user==null){
			this.addActionError("您还没有登录！请先登录去！");
			return LOGIN;
		}
		order.setUser(user);
		//在购物车订单提交后 返回上一页 后再次提交订单  购物车已经被清空  无物品可以提交的情况
		if (order.getTotal()==0) {
			this.addActionError("购物车已被清空！无订单可添加");
			return "msg";
		}
		orderService.save(order);
		//将数据显示到页面
		//由于order对象存在于值栈中  可以直接在页面获取
		// 订单添加后 清空购物车
		cart.clearCart();
		return "saveSuccess";
	}
	//接受page参数
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	//我的订单的查询  通过订单表的用户id查询 该用户的所有订单
	public String  findByUsid() {
		//获取用户
		User user=(User) ServletActionContext.getRequest().getSession().
				getAttribute("existUser");
		PageBean<Order> pageBean=orderService.findByUsid(user.getId(),page);
		//将数据存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUsidSuccess";
	}
	//根据订单id查询订单
	public String  findByOid() {
		order=orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}
	//接受支付通道 名称
	private  String pd_FrpId;
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	//为订单付款的方法
	public String payOrder() throws Exception {
		//修改订单
		Order currOrder=orderService.findByOid(order.getOid());
		//设置数据
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		//修改订单
		orderService.update(currOrder);
		//为订单付款
		//准备参数
		String p0_Cmd="Buy";			//设置支付类型
		String p1_MerId="10001126856";	//设置商户账号
		String p2_Order=order.getOid().toString();				//订单号
		String p3_Amt="0.01";		//设置支付金额
		String p4_Cur="CNY";			//设置货币类型
		String p5_Pid="";			//商品 名称
		String p6_Pcat=""; 			//商品种类
		String p7_Pdesc="";			//商品描述
		String p8_Url="http://localhost:8888/SSHShop/order_callBack.action";	//订单支付成功后的返回的地址
		String p9_SAF="";        //送货地址
		String pa_MP=""; 		//商品的扩展信息
		String pd_FrpId=this.pd_FrpId; 	//支付通道编码
		String  pr_NeedResponse="1";	//应答机制
		String keyValue="69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";	//秘钥
		String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat,
				p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);  //签名码
		//向易宝发出请求
		StringBuffer sb=new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		//重定向到易宝   
		ServletActionContext.getResponse().sendRedirect(sb.toString()); //有异常抛出
		
		return NONE;
	}
	//接受付款完成后返回的数据  
	private  String r3_Amt;//金额
	private String r6_Order; //订单编号
	public void setP3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	//付款成功后的转向页面
	public String  callBack() {
		//修改订单状态
		Order o=orderService.findByOid(Integer.parseInt(r6_Order));
		o.setState(2);
		orderService.update(o);
		//显示付款成功的信息
		this.addActionMessage("订单付款成功：订单编号："+r6_Order+",付款金额："+r3_Amt);
		return "msg";
	}
	//确认收货 修改订单状态
	public String  updateState() {
		//查询订单
		Order cuO=orderService.findByOid(order.getOid());
		cuO.setState(4);
		orderService.update(cuO);
		return "updateStateSuccess";
	}

}
