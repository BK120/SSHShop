package com.bk120.sshshop.utils;

import java.util.UUID;

/**
 * 生成随机字符串的工具类
 * @author BK120:任旭
 *
 */
public class UUIDUtils {
	//获得随机字符串的方法
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
