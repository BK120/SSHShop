package com.bk120.sshshop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.struts2.ServletActionContext;

import com.bk120.sshshop.user.service.UserService;
import com.bk120.sshshop.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 *跳转到用户注册界面的Action
 * @author BK120:任旭
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{
	//模型驱动使用对象
	private User user=new User();
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	//接受验证码
	private String checkCode;
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	//注入用户业务层
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/**
	 * 跳转到注册界面的方法
	 */
	public String  registPage() {
		return "registPage";
	}
	/**
	 * ajax异步校验查找用户是否已经存在
	 * @throws IOException 
	 */
	public String findByName() throws IOException {
		//获得Response对象
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setCharacterEncoding("utf-8");
		//调用Service
		User u=userService.findUserByUserName(user.getUsername());
		
		if(u!=null){
			//用户已经存在
			res.getWriter().write("<font color='red'>用户名已经存在！</font>");
		}else {
			//用户不存在
			res.getWriter().write("<font color='green'>用户名可以使用！</font>");
		}
		return NONE;
	}
	/**
	 * action提交验证注册表单
	 */
	public String regist(){
		//清空错误信息
				this.clearErrorsAndMessages();
				//判断验证码
				String che=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
				//忽略大小写
				if(!checkCode.equalsIgnoreCase(che)){
					//不相等
					this.addActionError("你的验证码输入错误！");
					return "registPage";
				}
		//新用户的各项数据校验完成 直接注册
		userService.save(user);
		this.addActionMessage("注册成功！请先去你的邮箱激活哟！");
		return "msg";
	}
	/**
	 * 用户激活
	 */
	public String active(){
		//查询用户是否存在  激活码是否被篡改 
		User u=userService.finUserByCode(user.getCode());
		if(u==null){
			//激活失败
			this.addActionMessage("激活失败：激活码错误！");
		}else {
			//激活成功,修改激活状态  并改为一次激活
			u.setState(1);
			u.setCode(null);
			//更新用户
			userService.update(u);
			this.addActionMessage("激活成功：请去登录！");
		}
		return "msg";
	}
	/**
	 * 跳转到登录界面
	 */
	public String  loginPage() {
		return "loginPage";
	}
	/**
	 * 登录的方法
	 */
	public String  login() {
		//清空错误信息
		this.clearErrorsAndMessages();
		//判断验证码
		String che=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		//忽略大小写
		if(!checkCode.equalsIgnoreCase(che)){
			//不相等
			this.addActionError("你的验证码输入错误！");
			return LOGIN;
		}
		//登录用户获得一个完整的用户信息  当前的uder对象只用用户名和密码
		User existUser=userService.login(user);
		if(existUser==null){
			//登录失败
			this.addActionError("登录失败:用户名或密码错误！或则用户未激活！");
			return LOGIN;
		}else{
			//登录成功
			//将用户信息存入Session中
			ServletActionContext.getRequest()
			.getSession().setAttribute("existUser", existUser);
			//页面跳转
			return "loginSuccess";
		}
	}
	/**
	 * 用户退出方法
	 */
	public String  quit() {
		//销毁Session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
	
}
