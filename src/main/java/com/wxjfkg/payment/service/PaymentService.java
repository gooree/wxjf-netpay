package com.wxjfkg.payment.service;

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

/**
 * 统一支付接口
 * 
 * @author GuoRui
 *
 */
public interface PaymentService {

	/**
	 * 预下单
	 * @param request
	 * @return
	 * @throws PaymentException
	 */
	CreateOrderResponse create(CreateOrderRequest request) throws PaymentException;
	
	/**
	 * 网站支付
	 * @param request
	 * @return
	 * @throws PaymentException
	 */
	@Deprecated
	WebPayResponse webpay(WebPayRequest request) throws PaymentException;
	
	/**
	 * 订单查询
	 * @param request
	 * @return
	 * @throws PaymentException
	 */
	QueryOrderResponse query(QueryOrderRequest request) throws PaymentException;
	
	/**
	 * 关闭订单
	 * @param request
	 * @return
	 * @throws PaymentException
	 */
	CloseOrderResponse close(CloseOrderRequest request) throws PaymentException;
	
	/**
	 * 申请退款
	 * @param request
	 * @return
	 * @throws PaymentException
	 */
	RefundResponse refund(RefundRequest request) throws PaymentException;
	
	/**
	 * 退款查询接口
	 * @param request
	 * @return
	 * @throws PaymentException
	 */
	RefundQueryResponse refundQuery(RefundQueryRequest request) throws PaymentException;
	
	/**
	 * 扫码支付
	 * @param request
	 * @return
	 * @throws PaymentException
	 */
	ScanPayResponse scanpay(ScanPayRequest request) throws PaymentException;
	
	/**
	 * 撤销订单
	 * @param request
	 * @return
	 * @throws PaymentException
	 */
	CancleOrderResponse cancle(CancleOrderRequest request) throws PaymentException;
	
}
