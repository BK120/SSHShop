package com.bk120.sshshop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.bk120.sshshop.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 后台操作权限拦截器
 * @author BK120:任旭
 *
 */
public class PrevilegeInterceptor extends MethodFilterInterceptor{
	
	private static final long serialVersionUID = 1L;

	//拦截器执行的方法
	@Override
	protected String doIntercept(ActionInvocation ai) throws Exception {
		//判断后台管理员是否登录过
		AdminUser adminUser=(AdminUser) ServletActionContext.getContext().getSession()
		.get("existAdminUser");
		System.out.println("及交互开流量看见可拦截啦可接路径");
		if(adminUser==null){
			//未登录
			ActionSupport aSupport=(ActionSupport) ai.getAction();
			aSupport.addActionError("亲！您还没有登录！没有权限访问！");
			return "loginFail";
		}else {
			//放行
			return ai.invoke();
		}
	}

}
