package com.wxjfkg.payment.core;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wxjfkg.payment.annotation.AlipayParam;
import com.wxjfkg.payment.utils.JsonUtils;

/**
 * 抽象请求类
 * 
 * @author GuoRui
 *
 */
public class AbstractRequest implements Serializable {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractRequest.class);
	
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private String channel;
	
	/**
	 * 同步返回URL
	 */
	private String returnUrl;
	
	/**
	 * 异步通知URL
	 */
	private String notifyUrl;
	
	/**
	 * sdk本地化参数
	 */
	private Map<String, String> localParams = new HashMap<String, String>();

	/**
	 * 交易渠道
	 * 
	 * @see com.wxjfkg.payment.core.PaymentConstant
	 */
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	
	public void put(String key, String value) {
		localParams.put(key, value);
	}
	
	/**
	 * 获取应用请求参数
	 * 
	 * @return
	 */
	public Map<String, String> getParameters() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.putAll(localParams);
		Field[] fields = this.getClass().getDeclaredFields();
		
		try {
			for (Field field : fields) {
				int mod = field.getModifiers();
				if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
					continue;
				}
				
				AlipayParam param = field.getAnnotation(AlipayParam.class);
				String paramName = (param == null) ? field.getName() : param.value();
	
				field.setAccessible(true);
				put(parameters, paramName, field.get(this));
			}
		} catch (Exception e) {
			logger.error("prepare parameters error,cannot access object field.", e);
			throw new RuntimeException(e);
		}
		return parameters;
	}
	
	private void put(Map<String, String> map, String key, Object value) {
		if(value != null) {
			String strValue;

			if (value instanceof String) {
				strValue = (String) value;
			} else if (value instanceof Integer) {
				strValue = ((Integer) value).toString();
			} else if (value instanceof Long) {
				strValue = ((Long) value).toString();
			} else if (value instanceof Float) {
				strValue = ((Float) value).toString();
			} else if (value instanceof Double) {
				strValue = ((Double) value).toString();
			} else if (value instanceof Boolean) {
				strValue = ((Boolean) value).toString();
			} else if (value instanceof Date) {
	            DateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
	            format.setTimeZone(TimeZone.getTimeZone(DATE_TIME_FORMAT));
				strValue = format.format((Date) value);
			} else {
				/*
				 * 其它参数一律转化为json格式进行传输
				 */
				strValue = JsonUtils.toJson(value);
			}
			
			map.put(key, strValue);
		}
	}
	
}
