package com.bk120.sshshop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.bk120.sshshop.adminuser.service.AdminUserService;
import com.bk120.sshshop.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台管理员的Action
 * @author BK120:任旭
 *
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{
	private AdminUser adminUser=new AdminUser();
	@Override
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	//注入Service
	private AdminUserService adminUserService;
	//后台登录的方法
	public String login() {
		//检查用户
		AdminUser existAdminUser=adminUserService.login(adminUser);
		if(existAdminUser==null){
			this.addActionError("亲！您的用户名或密码错误！");
			return "loginFail";
		}
		//登录成功 保存数据
		ServletActionContext.getContext().getSession()
		.put("existAdminUser", existAdminUser);
		return "loginSuccess";
	}
}
