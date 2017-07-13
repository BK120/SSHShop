package com.bk120.sshshop.user.adminaction;

import com.bk120.sshshop.user.service.UserService;
import com.bk120.sshshop.user.vo.User;
import com.bk120.sshshop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台管理前台用户操作的Action
 * @author BK120:任旭
 *
 */
public class AdminToUserAction extends ActionSupport implements ModelDriven<User>{
	private User user=new User();
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private UserService userService;
	//接受页面参数
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	//分页查询所有前台用户
	public String  findAll() {
		//分页查询所有用户
		PageBean<User> pageBean=userService.findByPage(page);
		//存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	//进入编辑用户界面
	public String  edit() {
		//查找一个用户
		user=userService.findById(user.getId());
		return "editSuccess";
	}
	//更新保存用户
	public String  update() {
		userService.update(user);
		return "updateSuccess";
	}

}
