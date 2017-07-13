package com.bk120.sshshop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bk120.sshshop.user.vo.User;
import com.bk120.sshshop.utils.PageHibernateCallback;

/**
 * 用户模块持久层代码
 * @author BK120:任旭
 *
 */
public class UserDao extends HibernateDaoSupport{
	//那名称查询是否由该用户
	public User findByUserName(String userName){
		String hql="from User where username = ?";
		List<User> list=this.getHibernateTemplate().find(hql,userName);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}
	//注册用户存入数据库的代码
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}
	//通过激活码查询用户
	public User findUserByCode(String code) {
		String hql="from User where code = ?";
		List<User> list=this.getHibernateTemplate().find(hql,code);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}
	//修改用户的方法
	public void update(User u) {
		this.getHibernateTemplate().update(u);
	}
	//用户登录的方法
	public User login(User user) {
		String hql="from User where username = ? and password = ? and state = ?";
		List<User> list=this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),1);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}
	//查询总的前台用户数
	public Integer findByCount() {
		String hql="select count(*) from User";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//分页查询前台用户数
	public List<User> findByPage(Integer begin, Integer limit) {
		String hql="from User order by id desc";
		List<User> list=this.getHibernateTemplate().execute(new PageHibernateCallback<User>(hql,
				null, begin, limit));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}
	//查找一个用户
	public User findById(Integer id) {
		return this.getHibernateTemplate().get(User.class, id);
	}
}
