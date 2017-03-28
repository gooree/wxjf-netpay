package com.wxjfkg.payment.alipay;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.wxjfkg.payment.core.ValidateResult;
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
import com.wxjfkg.payment.dto.refundquery.RefundInfo;
import com.wxjfkg.payment.dto.refundquery.RefundQueryRequest;
import com.wxjfkg.payment.dto.refundquery.RefundQueryResponse;
import com.wxjfkg.payment.dto.scanpay.ScanPayRequest;
import com.wxjfkg.payment.dto.scanpay.ScanPayResponse;
import com.wxjfkg.payment.dto.webpay.WebPayRequest;
import com.wxjfkg.payment.dto.webpay.WebPayResponse;
import com.wxjfkg.payment.exception.PaymentException;
import com.wxjfkg.payment.sdk.PaymentSdk;
import com.wxjfkg.payment.utils.JsonUtils;
import com.wxjfkg.payment.utils.ValidationUtils;

@Component
public class AlipaySdk implements PaymentSdk {
	
	@Autowired
	private AlipayClient alipayClient;

	@Override
	public CreateOrderResponse create(CreateOrderRequest request)
			throws PaymentException {
		ValidateResult validateResult = ValidationUtils.validate(request, "alipay");
		if (!validateResult.isSuccess()) {
			return new CreateOrderResponse("400", validateResult.getMessage());
		}
		BigDecimal amount = new BigDecimal(request.getTotalAmount());
		BigDecimal totalAmount = amount.divide(new BigDecimal(100), 2, RoundingMode.HALF_DOWN);
		request.setTotalAmount(totalAmount.toString());
		
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
		alipayRequest.setReturnUrl(request.getReturnUrl());
		alipayRequest.setNotifyUrl(request.getNotifyUrl());
		alipayRequest.setProdCode("QUICK_WAP_PAY");
		alipayRequest.setBizContent(JsonUtils.toJson(request.getParameters()));
		
		try {
			String form = alipayClient.pageExecute(alipayRequest).getBody();
			CreateOrderResponse response = new CreateOrderResponse(true);
			response.put("form", form);
			return response;
		} catch (Exception ex) {
			throw new PaymentException(ex);
		}
	}

	@Override
	@Deprecated
	public WebPayResponse webpay(WebPayRequest request) throws PaymentException {
		ValidateResult validateResult = ValidationUtils.validate(request, "alipay");
		if (!validateResult.isSuccess()) {
			return new WebPayResponse("400", validateResult.getMessage());
		}
		
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
		alipayRequest.setReturnUrl(request.getReturnUrl());
		alipayRequest.setNotifyUrl(request.getNotifyUrl());
		alipayRequest.setBizContent(JsonUtils.toJson(request.getParameters()));
		
		try {
			String form = alipayClient.pageExecute(alipayRequest).getBody();
			WebPayResponse response = new WebPayResponse(true);
			response.setForm(form);
			return response;
		} catch (Exception ex) {
			throw new PaymentException(ex);
		}
	}

	@Override
	public QueryOrderResponse query(QueryOrderRequest request)
			throws PaymentException {
		ValidateResult validateResult = ValidationUtils.validate(request, "alipay");
		if (!validateResult.isSuccess()) {
			return new QueryOrderResponse("400", validateResult.getMessage());
		}
		
		AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
		alipayRequest.setBizContent(JsonUtils.toJson(request.getParameters()));
		try {
			AlipayTradeQueryResponse alipayResponse = alipayClient.execute(alipayRequest);
			if(alipayResponse.isSuccess()) {
				QueryOrderResponse response = new QueryOrderResponse(true);
				response.setTradeNo(alipayResponse.getTradeNo());
				response.setOutTradeNo(alipayResponse.getOutTradeNo());
				response.setBuyerId(alipayResponse.getBuyerLogonId());
				response.setTotalAmount(alipayResponse.getTotalAmount());
				response.setTradeStatus(alipayResponse.getTradeStatus());
				return response;
			} else {
				return new QueryOrderResponse(alipayResponse.getSubCode(), alipayResponse.getSubMsg());
			}
		} catch (AlipayApiException e) {
			throw new PaymentException(e);
		}
	}

	@Override
	public CloseOrderResponse close(CloseOrderRequest request)
			throws PaymentException {
		ValidateResult validateResult = ValidationUtils.validate(request, "alipay");
		if (!validateResult.isSuccess()) {
			return new CloseOrderResponse("400", validateResult.getMessage());
		}
		
		AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
		alipayRequest.setBizContent(JsonUtils.toJson(request.getParameters()));
		
		try {
			AlipayTradeCloseResponse alipayResponse = alipayClient.execute(alipayRequest);
			if(alipayResponse.isSuccess()) {
				CloseOrderResponse response = new CloseOrderResponse(true);
				response.setTradeNo(alipayResponse.getTradeNo());
				response.setOutTradeNo(alipayResponse.getOutTradeNo());
				return response;
			} else {
				return new CloseOrderResponse(alipayResponse.getSubCode(), alipayResponse.getSubMsg());
			}
		} catch (AlipayApiException e) {
			throw new PaymentException(e);
		}
	}

	@Override
	public RefundResponse refund(RefundRequest request) throws PaymentException {
		ValidateResult validateResult = ValidationUtils.validate(request, "alipay");
		if (!validateResult.isSuccess()) {
			return new RefundResponse("400", validateResult.getMessage());
		}
		
		AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
		alipayRequest.setBizContent(JsonUtils.toJson(request.getParameters()));
		
		try {
			AlipayTradeRefundResponse alipayResponse = alipayClient.execute(alipayRequest);
			if(alipayResponse.isSuccess()) {
				RefundResponse response = new RefundResponse(true);
				response.setTradeNo(alipayResponse.getTradeNo());
				response.setOutTradeNo(alipayResponse.getOutTradeNo());
				response.setOutRefundNo(request.getOutRefundNo());
				response.setRefundAmount(request.getRefundAmount());
				return response;
			} else {
				return new RefundResponse(alipayResponse.getSubCode(), alipayResponse.getSubMsg());
			}
		} catch (AlipayApiException e) {
			throw new PaymentException(e);
		}
	}

	@Override
	public RefundQueryResponse refundQuery(RefundQueryRequest request)
			throws PaymentException {
		ValidateResult validateResult = ValidationUtils.validate(request, "alipay");
		if (!validateResult.isSuccess()) {
			return new RefundQueryResponse("400", validateResult.getMessage());
		}
		
		AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();
		alipayRequest.setBizContent(JsonUtils.toJson(request.getParameters()));
		
		try {
			AlipayTradeFastpayRefundQueryResponse alipayResponse = alipayClient.execute(alipayRequest);
			if(alipayResponse.isSuccess()) {
				RefundQueryResponse response = new RefundQueryResponse(true);
				response.setTradeNo(alipayResponse.getTradeNo());
				response.setOutTradeNo(alipayResponse.getOutTradeNo());
				response.setTotalAmount(alipayResponse.getTotalAmount());
				List<RefundInfo> refundList = new ArrayList<RefundInfo>();
				RefundInfo refund = new RefundInfo();
				refund.setOutRefundNo(alipayResponse.getOutRequestNo());
				refund.setRefundAmount(alipayResponse.getRefundAmount());
				refundList.add(refund);
				response.setRefundList(refundList);
				return response;
			} else {
				return new RefundQueryResponse(alipayResponse.getSubCode(), alipayResponse.getSubMsg());
			}
		} catch (AlipayApiException e) {
			throw new PaymentException(e);
		}
	}

	@Override
	public ScanPayResponse scanpay(ScanPayRequest request)
			throws PaymentException {
		ValidateResult validateResult = ValidationUtils.validate(request, "alipay");
		if (!validateResult.isSuccess()) {
			return new ScanPayResponse("400", validateResult.getMessage());
		}
		
		AlipayTradePayRequest alipayRequest = new AlipayTradePayRequest();
		request.setScene(request.getScene());
		alipayRequest.setBizContent(JsonUtils.toJson(request.getParameters()));
		
		try {
			AlipayTradePayResponse alipayResponse = alipayClient.execute(alipayRequest);
			if(alipayResponse.isSuccess()) {
				ScanPayResponse response = new ScanPayResponse(true);
				response.setTradeNo(alipayResponse.getTradeNo());
				response.setOutTradeNo(alipayResponse.getOutTradeNo());
				response.setTotalAmount(alipayResponse.getTotalAmount());
				response.setPayTime(alipayResponse.getGmtPayment());
				return response;
			} else {
				return new ScanPayResponse(alipayResponse.getSubCode(), alipayResponse.getSubMsg());
			}
		} catch (AlipayApiException e) {
			throw new PaymentException(e);
		}
	}

	@Override
	public CancleOrderResponse cancle(CancleOrderRequest request)
			throws PaymentException {
		ValidateResult validateResult = ValidationUtils.validate(request, "alipay");
		if (!validateResult.isSuccess()) {
			return new CancleOrderResponse("400", validateResult.getMessage());
		}
		
		AlipayTradeCancelRequest alipayRequest = new AlipayTradeCancelRequest();
		alipayRequest.setBizContent(JsonUtils.toJson(request.getParameters()));
		
		try {
			AlipayTradeCancelResponse alipayResponse = alipayClient.execute(alipayRequest);
			if(alipayResponse.isSuccess()) {
				CancleOrderResponse response = new CancleOrderResponse(true);
				response.setTradeNo(alipayResponse.getTradeNo());
				response.setOutTradeNo(alipayResponse.getOutTradeNo());
				response.setRetryFlag(alipayResponse.getRetryFlag());
				response.setAction(alipayResponse.getAction());
				return response;
			} else {
				return new CancleOrderResponse(alipayResponse.getSubCode(), alipayResponse.getSubMsg());
			}
		} catch (AlipayApiException e) {
			throw new PaymentException(e);
		}
	}
	
}
