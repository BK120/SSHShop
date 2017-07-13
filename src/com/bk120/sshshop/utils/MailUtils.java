package com.bk120.sshshop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送工具类
 * @author BK120:任旭
 *
 */
public class MailUtils {
	/**
	 * 发送邮件的方法
	 * @param to  收件人
	 * @param code 激活码
	 */
	public static void send(String to,String code) {
		/**
		 * 1：获得Session
		 * 2：获得代表邮件对象Message
		 * 3:发送邮件 Transport
		 */
		Properties props=new Properties();
		//设置发送主机
		props.setProperty("mail.host", "localhost");
		Session session=Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				//用系统账户连接服务器 
				return new PasswordAuthentication("Service@webshop.com","Service");
			}
		});
		Message message=new MimeMessage(session);
		//设置发件人
		try {
			message.setFrom(new InternetAddress("Service@webshop.com"));
			//抄送 CC  密送 BCC
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			//设置标题
			message.setSubject("来自WebShop网上购物商城的官方邮件");
			//设置邮件内容
			//////////////////////////////////////////////////////////////////////////////////
			//注意路径ip 尽量不要使用本地localhost
			message.setContent("<h2>来自WebShop网上购物商城的官方激活邮件！点此链接完成激活激活：</h2><h3><a href='http://192.168.1.108:8888/SSHShop/user_active.action?code="+code+"'>"
					+ "http://192.168.1.108:8888/SSHShop/user_active.action?code="+code+"</a></h3>",
					"text/html;charset=utf-8");
			//发送邮件
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*public static void main(String[] args) {
		send("bk120@webshop.com", "124577744112224545125");
	}*/
}
