package com.wxjfkg.payment.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wxjfkg.payment.core.PaymentConstant;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class AlipayServiceTest {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PaymentService paymentService;

	@Test
	public void testAlipayCreate() throws PaymentException {
		CreateOrderRequest request = new CreateOrderRequest();
		request.setChannel(PaymentConstant.ALIPAY_WAP);
		request.setBody("Iphone6 16G");
		request.setOutTradeNo("20170321000001");
		request.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
		request.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");
		request.setTotalAmount("1");
		request.setProductCode("QUICK_WAP_PAY");
		CreateOrderResponse response = paymentService.create(request);
		Assert.assertTrue(response.isSuccess());
//		Assert.assertEquals("400", response.getErrorCode());
//		Assert.assertFalse(response.isSuccess());
	}

	@Test
	public void testAlipayWebpay() throws PaymentException {
		WebPayRequest request = new WebPayRequest();
		request.setChannel(PaymentConstant.ALIPAY_WAP);
		WebPayResponse response = paymentService.webpay(request);
		Assert.assertTrue(response.isSuccess());
	}

	@Test
	public void testAlipayQuery() throws PaymentException {
		QueryOrderRequest request = new QueryOrderRequest();
		request.setChannel(PaymentConstant.ALIPAY_WAP);
		request.setOutTradeNo("20170321000001");
		QueryOrderResponse response = paymentService.query(request);
		Assert.assertTrue(response.isSuccess());
//		Assert.assertEquals("400", response.getErrorCode());
//		Assert.assertFalse(response.isSuccess());
	}

	@Test
	public void testAlipayClose() throws PaymentException {
		CloseOrderRequest request = new CloseOrderRequest();
		request.setChannel(PaymentConstant.ALIPAY_WAP);
		request.setOutTradeNo("20170321000001");
		CloseOrderResponse response = paymentService.close(request);
		Assert.assertTrue(response.isSuccess());
//		Assert.assertEquals("400", response.getErrorCode());
//		Assert.assertFalse(response.isSuccess());
	}

	@Test
	public void testAlipayRefund() throws PaymentException {
		RefundRequest request = new RefundRequest();
		request.setChannel(PaymentConstant.ALIPAY_WAP);
		request.setOutTradeNo("20170321000001");
		request.setOutRefundNo("20170321000001");
		request.setRefundAmount("1");
		RefundResponse response = paymentService.refund(request);
		Assert.assertTrue(response.isSuccess());
//		Assert.assertEquals("400", response.getErrorCode());
//		Assert.assertFalse(response.isSuccess());
	}

	@Test
	public void testAlipayRefundQuery() throws PaymentException {
		RefundQueryRequest request = new RefundQueryRequest();
		request.setChannel(PaymentConstant.ALIPAY_WAP);
		request.setOutTradeNo("20170321000001");
		request.setOutRefundNo("20170321000001");
		RefundQueryResponse response = paymentService.refundQuery(request);
		Assert.assertFalse(response.isSuccess());
//		Assert.assertEquals("400", response.getErrorCode());
//		Assert.assertFalse(response.isSuccess());
	}

	@Test
	public void testAlipayScanPay() throws PaymentException {
		ScanPayRequest request = new ScanPayRequest();
		request.setChannel(PaymentConstant.ALIPAY_QR);
		request.setScene("bar_code");
		request.setSubject("Iphone6 16G");
		request.setOutTradeNo("20150320010101001");
		request.setAuthCode("28763443825664394");
		ScanPayResponse response = paymentService.scanpay(request);
		Assert.assertTrue(response.isSuccess());
//		Assert.assertEquals("400", response.getErrorCode());
//		Assert.assertFalse(response.isSuccess());
	}

	@Test
	public void testAlipayCancle() throws PaymentException {
		CancleOrderRequest request = new CancleOrderRequest();
		request.setChannel(PaymentConstant.ALIPAY_QR);
		request.setOutTradeNo("20170321000002");
		CancleOrderResponse response = paymentService.cancle(request);
		Assert.assertTrue(response.isSuccess());
//		Assert.assertEquals("400", response.getErrorCode());
//		Assert.assertFalse(response.isSuccess());
	}
	
}
