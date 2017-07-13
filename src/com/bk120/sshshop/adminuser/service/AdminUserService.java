package com.bk120.sshshop.adminuser.service;
/**
 * 后台业务层
 * @author BK120:任旭
 */

import org.springframework.transaction.annotation.Transactional;

import com.bk120.sshshop.adminuser.dao.AdminUserDao;
import com.bk120.sshshop.adminuser.vo.AdminUser;

@Transactional
public class AdminUserService {
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
	//业务层登录
	public AdminUser login(AdminUser adminUser) {
		
		return adminUserDao.login(adminUser);
	}
}
