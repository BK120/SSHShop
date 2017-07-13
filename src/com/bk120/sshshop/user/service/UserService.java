package com.bk120.sshshop.user.service;
/**
 * 用户模块业务层代码
 * @author BK120:任旭
 *
 */

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bk120.sshshop.order.vo.Order;
import com.bk120.sshshop.user.dao.UserDao;
import com.bk120.sshshop.user.vo.User;
import com.bk120.sshshop.utils.MailUtils;
import com.bk120.sshshop.utils.PageBean;
import com.bk120.sshshop.utils.UUIDUtils;
@Transactional
public class UserService {
	//注入UserDao
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	//用户名去查询用户的方法
	public User findUserByUserName(String userName){
		return userDao.findByUserName(userName);
	}
	//完成用户注册代码
	public void save(User user) {
		user.setState(0);//0:代表 用户未激活，1：代表用户已经激活
		String code=UUIDUtils.getUUID()+UUIDUtils.getUUID();//变成64位
		user.setCode(code);
		userDao.save(user);
		//发送激活邮件
		MailUtils.send(user.getEmail(), user.getCode());
	}
	/**
	 * 通过激活码查询用户是否存在
	 * @param code
	 */
	public User finUserByCode(String code) {
		return userDao.findUserByCode(code);
	}
	/**
	 * 修改用户
	 * @param u 新用户
	 */
	public void update(User u) {
		userDao.update(u);
	}
	/**
	 * 用户登录的方法
	 * @param user
	 * @return
	 */
	public User login(User user) {
		return userDao.login(user);
	}
	//分页查询所有用户
	public PageBean<User> findByPage(Integer page) {
		PageBean<User> pageBean=new PageBean<User>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示用户数
		Integer limit=8;
		pageBean.setLimit(limit);
		//设置总的记录数
		Integer totalcount=0;
		totalcount=userDao.findByCount();
		pageBean.setTotalCount(totalcount);
		//设置总页数
		Integer totalPage=0;
		if(totalcount%limit==0){
			totalPage=totalcount/limit;
		}else {
			totalPage=totalcount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页显示的数据集合
		Integer begin=(page-1)*limit;
		List<User> list=userDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//查找一个用户
	public User findById(Integer id) {
		return userDao.findById(id);
	}
}
