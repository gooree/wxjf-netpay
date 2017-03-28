/*
 * Copyright (C), 2015-2016, 五星金服控股有限公司
 * FileName: MD5.java
 * Author:   Terry.Xu
 * Date:     Aug 27, 2014 5:55:26 PM
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.wxjfkg.payment.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;


/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author Andy_Wang01
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MD5 {

	public static final String CHARSET = "UTF-8";

	/**
	 * 使用ThreadLocal以空间换时间解决MD5线程安全问题
	 */
	@SuppressWarnings("rawtypes")
	private static ThreadLocal threadLocal = new ThreadLocal() {
		protected synchronized Object initialValue() {
			try {
				return MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				return null;
			}
		}
	};

	private static MessageDigest getMessageDigest() {
		return (MessageDigest) threadLocal.get();
	}

	public static String digest(String s, String charset) throws UnsupportedEncodingException {
		charset = StringUtils.isBlank(charset)?CHARSET:charset;
		getMessageDigest().update(s.getBytes(charset));
		return HexUtil.bytes2Hexstr(getMessageDigest().digest());
	}

	public static String getKeyedDisgest(String src, String key) {
		try {
			MessageDigest md5 = getMessageDigest();
			md5.update(src.getBytes(CHARSET));
			String result = "";
			byte[] temp;
			temp = md5.digest(key.getBytes(CHARSET));
			for (int i = 0; i < temp.length; i++) {
				result += Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
