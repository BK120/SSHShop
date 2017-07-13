package com.bk120.sshshop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bk120.sshshop.adminuser.vo.AdminUser;

/**
 * 后台持久层
 * @author BK120:任旭
 *
 */
public class AdminUserDao extends HibernateDaoSupport{
	//登录的方法
	public AdminUser login(AdminUser adminUser) {
		String hql="from AdminUser where username=? and password=?";
		List<AdminUser> list=this.getHibernateTemplate().find(hql,adminUser.getUsername(),adminUser.getPassword());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
