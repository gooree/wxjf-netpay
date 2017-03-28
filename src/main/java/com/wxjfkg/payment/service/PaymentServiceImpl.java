package com.wxjfkg.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxjfkg.payment.dto.cancle.CancleOrderRequest;
import com.wxjfkg.payment.dto.cancle.CancleOrderResponse;
import com.wxjfkg.payment.dto.close.CloseOrderRequest;
import com.wxjfkg.payment.dto.close.CloseOrderResponse;
import com.wxjfkg.payment.dto.create.CreateOrderRequest;
import com.wxjfkg.payment.dto.create.CreateOrderResponse;
import com.wxjfkg.payment.dto.query.QueryOrderRequest;
import com.wxjfkg.payment.dto.query.QueryOrderResponse;
import com.wxjfkg.payment.dto.refund.RefundRequest;
import com.wxjfkg.payment.dto.refund.RefundResponse;
import com.wxjfkg.payment.dto.refundquery.RefundQueryRequest;
import com.wxjfkg.payment.dto.refundquery.RefundQueryResponse;
import com.wxjfkg.payment.dto.scanpay.ScanPayRequest;
import com.wxjfkg.payment.dto.scanpay.ScanPayResponse;
import com.wxjfkg.payment.dto.webpay.WebPayRequest;
import com.wxjfkg.payment.dto.webpay.WebPayResponse;
import com.wxjfkg.payment.exception.PaymentException;
import com.wxjfkg.payment.sdk.PaymentSdk;
import com.wxjfkg.payment.sdk.PaymentSdkFactoryBean;

/**
 * 统一支付接口实现
 * 
 * @author GuoRui
 *
 */
@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentSdkFactoryBean sdkFactory;

	@Override
	public CreateOrderResponse create(CreateOrderRequest request)
			throws PaymentException {
		PaymentSdk sdk = sdkFactory.getPaymentSDK(request);
		return sdk.create(request);
	}

	@Override
	public WebPayResponse webpay(WebPayRequest request) throws PaymentException {
		PaymentSdk sdk = sdkFactory.getPaymentSDK(request);
		return sdk.webpay(request);
	}

	@Override
	public QueryOrderResponse query(QueryOrderRequest request)
			throws PaymentException {
		PaymentSdk sdk = sdkFactory.getPaymentSDK(request);
		return sdk.query(request);
	}

	@Override
	public CloseOrderResponse close(CloseOrderRequest request)
			throws PaymentException {
		PaymentSdk sdk = sdkFactory.getPaymentSDK(request);
		return sdk.close(request);
	}

	@Override
	public RefundResponse refund(RefundRequest request) throws PaymentException {
		PaymentSdk sdk = sdkFactory.getPaymentSDK(request);
		return sdk.refund(request);
	}

	@Override
	public RefundQueryResponse refundQuery(RefundQueryRequest request)
			throws PaymentException {
		PaymentSdk sdk = sdkFactory.getPaymentSDK(request);
		return sdk.refundQuery(request);
	}

	@Override
	public ScanPayResponse scanpay(ScanPayRequest request)
			throws PaymentException {
		PaymentSdk sdk = sdkFactory.getPaymentSDK(request);
		return sdk.scanpay(request);
	}

	@Override
	public CancleOrderResponse cancle(CancleOrderRequest request)
			throws PaymentException {
		PaymentSdk sdk = sdkFactory.getPaymentSDK(request);
		return sdk.cancle(request);
	}

	
}
