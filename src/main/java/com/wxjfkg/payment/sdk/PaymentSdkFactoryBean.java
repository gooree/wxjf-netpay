package com.wxjfkg.payment.sdk;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wxjfkg.payment.alipay.AlipaySdk;
import com.wxjfkg.payment.core.AbstractRequest;
import com.wxjfkg.payment.wxpay.WechatPaySdk;

@Component
public class PaymentSdkFactoryBean {

	@Autowired
	private AlipaySdk alipaySdk;

	@Autowired
	private WechatPaySdk wxpaySdk;
	
	private static final String ALI_PREFIX = "alipay";
	
	private static final String WX_PREFIX = "wx";
	
	public PaymentSdk getPaymentSDK(AbstractRequest request) {
		String channel = request.getChannel();
		if (StringUtils.isBlank(channel)) {
			throw new IllegalArgumentException(
					"channel can't be empty or null!");
		}
		if (channel.startsWith(ALI_PREFIX)) {
			return alipaySdk;
		} else if (channel.startsWith(WX_PREFIX)) {
			return wxpaySdk;
		}
		throw new IllegalArgumentException("channel " + channel
				+ " is not supported.");
	}
	
}
