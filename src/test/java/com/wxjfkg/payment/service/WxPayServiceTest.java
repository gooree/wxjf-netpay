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
public class WxPayServiceTest {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PaymentService paymentService;

	@Test
	public void testWxPayCreate() throws PaymentException {
		CreateOrderRequest request = new CreateOrderRequest();
		request.setChannel(PaymentConstant.WX_PUB);
		request.setBody("JSAPI支付测试");
		request.setOutTradeNo("20170321000001");
		request.setNotifyUrl("http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php");
		request.setTotalAmount("1");
		request.setUserId("owiuzvy4QvYAN4zxteVxZD8WbUKo");
		CreateOrderResponse response = paymentService.create(request);
		Assert.assertTrue(response.isSuccess());
//		Assert.assertEquals("400", response.getErrorCode());
//		Assert.assertFalse(response.isSuccess());
	}

	@Test
	public void testWxPayWebpay() throws PaymentException {
		WebPayRequest request = new WebPayRequest();
		request.setChannel(PaymentConstant.WX_PUB);
		WebPayResponse response = paymentService.webpay(request);
		Assert.assertTrue(response.isSuccess());
	}

	@Test
	public void testWxPayQuery() throws PaymentException {
		QueryOrderRequest request = new QueryOrderRequest();
		request.setChannel(PaymentConstant.WX_PUB);
		request.setOutTradeNo("20170321000001");
		QueryOrderResponse response = paymentService.query(request);
		Assert.assertTrue(response.isSuccess());
//		Assert.assertEquals("400", response.getErrorCode());
//		Assert.assertFalse(response.isSuccess());
	}

	@Test
	public void testWxPayClose() throws PaymentException {
		CloseOrderRequest request = new CloseOrderRequest();
		request.setChannel(PaymentConstant.WX_PUB);
		request.setOutTradeNo("20170321000001");
		CloseOrderResponse response = paymentService.close(request);
		Assert.assertTrue(response.isSuccess());
//		Assert.assertEquals("400", response.getErrorCode());
//		Assert.assertFalse(response.isSuccess());
	}

	@Test
	public void testWxPayRefund() throws PaymentException {
		RefundRequest request = new RefundRequest();
		request.setChannel(PaymentConstant.WX_PUB);
		request.setOutTradeNo("20170321000001");
		request.setOutRefundNo("20170321000001");
		request.setTotalAmount("1");
		request.setRefundAmount("1");
		RefundResponse response = paymentService.refund(request);
		Assert.assertTrue(response.isSuccess());
//		Assert.assertEquals("400", response.getErrorCode());
//		Assert.assertFalse(response.isSuccess());
	}

	@Test
	public void testWxPayRefundQuery() throws PaymentException {
		RefundQueryRequest request = new RefundQueryRequest();
		request.setChannel(PaymentConstant.WX_PUB);
		request.setOutTradeNo("20170321000001");
		request.setOutRefundNo("20170321000001");
		RefundQueryResponse response = paymentService.refundQuery(request);
		Assert.assertFalse(response.isSuccess());
//		Assert.assertEquals("400", response.getErrorCode());
//		Assert.assertFalse(response.isSuccess());
	}

	@Test
	public void testWxPayScanPay() throws PaymentException {
		ScanPayRequest request = new ScanPayRequest();
		request.setChannel(PaymentConstant.WX_PUB);
		request.setDeviceInfo("1000");
		request.setSubject("刷卡支付测试");
		request.setOutTradeNo("20170321000002");
		request.setTotalAmount("1");
		request.setTerminalId("14.17.22.52");
		request.setAuthCode("120269300684844649");
		ScanPayResponse response = paymentService.scanpay(request);
		Assert.assertTrue(response.isSuccess());
//		Assert.assertEquals("400", response.getErrorCode());
//		Assert.assertFalse(response.isSuccess());
	}

	@Test
	public void testWxPayCancle() throws PaymentException {
		CancleOrderRequest request = new CancleOrderRequest();
		request.setChannel(PaymentConstant.WX_PUB);
		request.setOutTradeNo("20170321000002");
		CancleOrderResponse response = paymentService.cancle(request);
		Assert.assertTrue(response.isSuccess());
//		Assert.assertEquals("400", response.getErrorCode());
//		Assert.assertFalse(response.isSuccess());
	}

}
