package com.wxjfkg.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wxjfkg.sdk.wxpay.WxPayClient;

@Configuration
public class WxpayClientConfig {

	@Value("${wxpay.appId}")
	private String appId;
	
	@Value("${wxpay.appsecret}")
	private String appsecret;

	@Value("${wxpay.mchId}")
	private String mchId;
	
	@Bean
	public WxPayClient wxpayClient() {
		WxPayClient client = new WxPayClient(appId, appsecret, mchId, "WEB");
		return client;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

}
