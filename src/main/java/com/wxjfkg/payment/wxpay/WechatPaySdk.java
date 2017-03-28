package com.wxjfkg.payment.wxpay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import com.wxjfkg.payment.utils.ValidationUtils;
import com.wxjfkg.sdk.http.HttpApiResponse;
import com.wxjfkg.sdk.wxpay.WxPayClient;
import com.wxjfkg.sdk.wxpay.model.RefundOrderData;
import com.wxjfkg.sdk.wxpay.request.MicroPayRequest;
import com.wxjfkg.sdk.wxpay.request.OrderQueryRequest;
import com.wxjfkg.sdk.wxpay.request.ReverseRequest;
import com.wxjfkg.sdk.wxpay.request.UnifiedOrderRequest;
import com.wxjfkg.sdk.wxpay.response.MicroPayResponse;
import com.wxjfkg.sdk.wxpay.response.OrderQueryResponse;
import com.wxjfkg.sdk.wxpay.response.ReverseResponse;
import com.wxjfkg.sdk.wxpay.response.UnifiedOrderResponse;

@Component
public class WechatPaySdk implements PaymentSdk {
	
	@Autowired
	private WxPayClient wxpayClient;

	@Override
	public CreateOrderResponse create(CreateOrderRequest request)
			throws PaymentException {
		ValidateResult validateResult = ValidationUtils.validate(request, "wxpay");
		if (!validateResult.isSuccess()) {
			return new CreateOrderResponse("400", validateResult.getMessage());
		}
		
		UnifiedOrderRequest wxpayRequest = new UnifiedOrderRequest();
		wxpayRequest.setBody(request.getBody());
		wxpayRequest.setOut_trade_no(request.getOutTradeNo());
		wxpayRequest.setFee_type("CNY");
		wxpayRequest.setTotal_fee(Integer.valueOf(request.getTotalAmount()));
		wxpayRequest.setSpbill_create_ip("127.0.0.1");
		wxpayRequest.setNotify_url(request.getNotifyUrl());
		wxpayRequest.setTrade_type("JSAPI");
		wxpayRequest.setOpenid(request.getUserId());
		
		try {
			HttpApiResponse<UnifiedOrderResponse> apiResponse = wxpayClient
					.execute(wxpayRequest, UnifiedOrderResponse.class);
			UnifiedOrderResponse wxpayResponse = apiResponse.getEntity();
			if(wxpayResponse.isSuccess()) {
				CreateOrderResponse response = new CreateOrderResponse(true);
				response.put("appId", wxpayResponse.getAppid());
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				String timestamp = format.format(new Date());
				response.put("timeStamp", timestamp);
				response.put("nonceStr", wxpayResponse.getNonce_str());
				response.put("package", "prepay_id=" + wxpayResponse.getPrepay_id());
				response.put("signType", "MD5");
				response.put("paySign", wxpayResponse.getSign());
				response.put("openid", request.getUserId());
				return response;
			} else {
				return new CreateOrderResponse(wxpayResponse.getErr_code(),
						wxpayResponse.getErr_code_des());
			}
		} catch (Exception ex) {
			throw new PaymentException(ex);
		}
	}

	@Override
	@Deprecated
	public WebPayResponse webpay(WebPayRequest request) throws PaymentException {
		ValidateResult validateResult = ValidationUtils.validate(request, "wxpay");
		if (!validateResult.isSuccess()) {
			return new WebPayResponse("400", validateResult.getMessage());
		}
		
		WebPayResponse response = new WebPayResponse(true);
		return response;
	}

	@Override
	public QueryOrderResponse query(QueryOrderRequest request)
			throws PaymentException {
		ValidateResult validateResult = ValidationUtils.validate(request, "wxpay");
		if (!validateResult.isSuccess()) {
			return new QueryOrderResponse("400", validateResult.getMessage());
		}
		
		OrderQueryRequest wxpayRequest = new OrderQueryRequest();
		wxpayRequest.setTransaction_id(request.getTradeNo());
		wxpayRequest.setOut_trade_no(request.getOutTradeNo());
		
		try {
			HttpApiResponse<OrderQueryResponse> apiResponse = wxpayClient
					.execute(wxpayRequest, OrderQueryResponse.class);
			OrderQueryResponse wxpayResponse = apiResponse.getEntity();
			if(wxpayResponse.isSuccess()) {
				QueryOrderResponse response = new QueryOrderResponse(true);
				response.setBuyerId(wxpayResponse.getOpenid());
				response.setTradeNo(wxpayResponse.getTransaction_id());
				response.setOutTradeNo(wxpayResponse.getOut_trade_no());
				response.setTotalAmount(wxpayResponse.getTotal_fee());
				response.setTradeStatus(wxpayResponse.getTrade_state());
				
				return response;
			} else {
				return new QueryOrderResponse(wxpayResponse.getErr_code(),
						wxpayResponse.getErr_code_des());
			}
		} catch (Exception ex) {
			throw new PaymentException(ex);
		}
	}

	@Override
	public CloseOrderResponse close(CloseOrderRequest request)
			throws PaymentException {
		ValidateResult validateResult = ValidationUtils.validate(request, "wxpay");
		if (!validateResult.isSuccess()) {
			return new CloseOrderResponse("400", validateResult.getMessage());
		}
		
		com.wxjfkg.sdk.wxpay.request.CloseOrderRequest wxpayRequest = new com.wxjfkg.sdk.wxpay.request.CloseOrderRequest();
		wxpayRequest.setOut_trade_no(request.getOutTradeNo());
		
		try {
			HttpApiResponse<com.wxjfkg.sdk.wxpay.response.CloseOrderResponse> apiResponse = wxpayClient
					.execute(
							wxpayRequest,
							com.wxjfkg.sdk.wxpay.response.CloseOrderResponse.class);
			com.wxjfkg.sdk.wxpay.response.CloseOrderResponse wxpayResponse = apiResponse.getEntity();
			
			if(wxpayResponse.isSuccess()) {
				CloseOrderResponse response = new CloseOrderResponse(true);
				response.setOutTradeNo(request.getOutTradeNo());
				
				return response;
			} else {
				return new CloseOrderResponse(wxpayResponse.getErr_code(),
						wxpayResponse.getErr_code_des());
			}
		} catch (Exception ex) {
			throw new PaymentException(ex);
		}
	}

	@Override
	public RefundResponse refund(RefundRequest request) throws PaymentException {
		ValidateResult validateResult = ValidationUtils.validate(request, "wxpay");
		if (!validateResult.isSuccess()) {
			return new RefundResponse("400", validateResult.getMessage());
		}
		
		com.wxjfkg.sdk.wxpay.request.RefundRequest wxpayRequest = new com.wxjfkg.sdk.wxpay.request.RefundRequest();
		wxpayRequest.setTransaction_id(request.getTradeNo());
		wxpayRequest.setOut_trade_no(request.getOutTradeNo());
		wxpayRequest.setOut_refund_no(request.getOutRefundNo());
		wxpayRequest.setTotal_fee(Integer.parseInt(request.getTotalAmount()));
		wxpayRequest.setRefund_fee(Integer.parseInt(request.getRefundAmount()));
		wxpayRequest.setOp_user_id(wxpayClient.getMchId());
		
		try {
			HttpApiResponse<com.wxjfkg.sdk.wxpay.response.RefundResponse> apiResponse = wxpayClient
					.execute(
							wxpayRequest,
							com.wxjfkg.sdk.wxpay.response.RefundResponse.class);
			com.wxjfkg.sdk.wxpay.response.RefundResponse wxpayResponse = apiResponse.getEntity();
			
			if(wxpayResponse.isSuccess()) {
				RefundResponse response = new RefundResponse(true);
				response.setTradeNo(wxpayResponse.getTransaction_id());
				response.setOutTradeNo(wxpayResponse.getOut_trade_no());
				response.setRefundNo(wxpayResponse.getRefund_id());
				response.setOutRefundNo(wxpayResponse.getOut_refund_no());
				response.setRefundAmount(wxpayResponse.getRefund_fee());
				
				return response;
			} else {
				return new RefundResponse(wxpayResponse.getErr_code(),
						wxpayResponse.getErr_code_des());
			}
		} catch (Exception ex) {
			throw new PaymentException(ex);
		}
	}

	@Override
	public RefundQueryResponse refundQuery(RefundQueryRequest request)
			throws PaymentException {
		ValidateResult validateResult = ValidationUtils.validate(request, "wxpay");
		if (!validateResult.isSuccess()) {
			return new RefundQueryResponse("400", validateResult.getMessage());
		}
		
		com.wxjfkg.sdk.wxpay.request.RefundQueryRequest wxpayRequest = new com.wxjfkg.sdk.wxpay.request.RefundQueryRequest();
		wxpayRequest.setTransaction_id(request.getTradeNo());
		wxpayRequest.setOut_trade_no(request.getOutTradeNo());
		wxpayRequest.setOut_refund_no(request.getOutRefundNo());
		wxpayRequest.setRefund_id(request.getRefundNo());
		
		try {
			HttpApiResponse<com.wxjfkg.sdk.wxpay.response.RefundQueryResponse> apiResponse = wxpayClient
					.execute(
							wxpayRequest,
							com.wxjfkg.sdk.wxpay.response.RefundQueryResponse.class);
			com.wxjfkg.sdk.wxpay.response.RefundQueryResponse wxpayResponse = apiResponse.getEntity();
			
			if(wxpayResponse.isSuccess()) {
				RefundQueryResponse response = new RefundQueryResponse(true);
				response.setTradeNo(wxpayResponse.getTransaction_id());
				response.setOutTradeNo(wxpayResponse.getOut_trade_no());
				response.setTotalAmount(wxpayResponse.getTotal_fee());
				
//				Integer refundAmount = 0;
				List<RefundOrderData> refunds = wxpayResponse.getRefundList();
				List<RefundInfo> refundList = new ArrayList<RefundInfo>();
				if (refunds != null && refunds.size() > 0) {
					for (RefundOrderData refund : refunds) {
						RefundInfo refundInfo = new RefundInfo();
						refundInfo.setOutRefundNo(refund.getOutRefundNo());
						refundInfo.setRefundNo(refund.getRefundID());
						refundInfo.setRefundAmount(String.valueOf(refund
								.getRefundFee()));
//						refundAmount = refundAmount + refund.getRefundFee();
						refundList.add(refundInfo);
					}
				}
				response.setRefundList(refundList);
				
				return response;
			} else {
				return new RefundQueryResponse(wxpayResponse.getErr_code(),
						wxpayResponse.getErr_code_des());
			}
		} catch (Exception ex) {
			throw new PaymentException(ex);
		}
	}

	@Override
	public ScanPayResponse scanpay(ScanPayRequest request)
			throws PaymentException {
		ValidateResult validateResult = ValidationUtils.validate(request, "wxpay");
		if (!validateResult.isSuccess()) {
			return new ScanPayResponse("400", validateResult.getMessage());
		}
		
		MicroPayRequest wxpayRequest = new MicroPayRequest();
		wxpayRequest.setBody(request.getSubject());
		wxpayRequest.setDetail(request.getBody());
		wxpayRequest.setOut_trade_no(request.getOutTradeNo());
		wxpayRequest.setTotal_fee(Integer.valueOf(request.getTotalAmount()));
		wxpayRequest.setSpbill_create_ip(request.getTerminalId());
		wxpayRequest.setAuth_code(request.getAuthCode());
		
		wxpayClient.setDeviceInfo(request.getDeviceInfo());
		try {
			HttpApiResponse<MicroPayResponse> apiResponse = wxpayClient
					.execute(wxpayRequest, MicroPayResponse.class);
			MicroPayResponse wxpayResponse = apiResponse.getEntity();
			
			if(wxpayResponse.isSuccess()) {
				ScanPayResponse response = new ScanPayResponse(true);
				response.setTradeNo(wxpayResponse.getTransaction_id());
				response.setOutTradeNo(wxpayResponse.getOut_trade_no());
				response.setTotalAmount(wxpayResponse.getTotal_fee());
				response.setUserId(wxpayResponse.getOpenid());
				
				return response;
			} else {
				return new ScanPayResponse(wxpayResponse.getErr_code(),
						wxpayResponse.getErr_code_des());
			}
		} catch (Exception ex) {
			throw new PaymentException(ex);
		}
	}

	@Override
	public CancleOrderResponse cancle(CancleOrderRequest request)
			throws PaymentException {
		ValidateResult validateResult = ValidationUtils.validate(request, "wxpay");
		if (!validateResult.isSuccess()) {
			return new CancleOrderResponse("400", validateResult.getMessage());
		}
		
		ReverseRequest wxpayRequest = new ReverseRequest();
		wxpayRequest.setOut_trade_no(request.getOutTradeNo());
		
		try {
			HttpApiResponse<ReverseResponse> apiResponse = wxpayClient
					.execute(wxpayRequest, ReverseResponse.class);
			ReverseResponse wxpayResponse = apiResponse.getEntity();
			
			if(wxpayResponse.isSuccess()) {
				CancleOrderResponse response = new CancleOrderResponse(true);
				response.setRetryFlag(wxpayResponse.getRecall());
				
				return response;
			} else {
				return new CancleOrderResponse(wxpayResponse.getErr_code(),
						wxpayResponse.getErr_code_des());
			}
		} catch (Exception ex) {
			throw new PaymentException(ex);
		}
	}

}
