package com.wxjfkg.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

@Configuration
public class AlipayClientConfig {
	
	private static final String CHARSET = "UTF-8";

	private static final String SIGN_TYPE = "RSA2";

	private static final String FORMAT = "json";
	
	@Value("${alipay.gateway}")
	private String gateway;
	
	@Value("${alipay.appId}")
	private String appId;
	
	@Value("${alipay.privateKey}")
	private String privateKey;
	
	@Value("${alipay.publicKey}")
	private String publicKey;

	@Bean
	public AlipayClient alipayClient() {
		AlipayClient alipayClient = new DefaultAlipayClient(gateway, appId,
				privateKey, FORMAT, CHARSET, publicKey, SIGN_TYPE);
		return alipayClient;
	}
	
}
