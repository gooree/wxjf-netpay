package com.wxjfkg.payment.alipay;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wxjfkg.PaymentApplication;
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
@SpringBootTest(classes = PaymentApplication.class)
public class AlipaySdkTest {

	private static Logger logger = LoggerFactory.getLogger(AlipaySdkTest.class);
	
	@Autowired
	private AlipaySdk sdk;
	
	private String tradeNo;
	
	private String outTradeNo;
	
	@Before
	public void setup() {
		outTradeNo = "20170319010101001";
	}
	
	@Test
	public void testCreate() throws PaymentException {
		CreateOrderRequest request = new CreateOrderRequest();
		CreateOrderResponse response = sdk.create(request);
		Assert.assertTrue(response.isSuccess());
	}
	
	@Test
	public void testWebpay() throws PaymentException {
		WebPayRequest request = new WebPayRequest();
		request.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
		request.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");
		request.setOutTradeNo(tradeNo);
		request.setTotalAmount("88.88");
		request.setSubject("Iphone6 16G");
		request.put("product_code", "QUICK_WAP_PAY");
		WebPayResponse response = sdk.webpay(request);
		logger.info("return form: {}", response.getForm());
		Assert.assertTrue(response.isSuccess());
	}
	
	@Test
	public void testQuery() throws PaymentException {
		QueryOrderRequest request = new QueryOrderRequest();
		request.setOutTradeNo(outTradeNo);
		QueryOrderResponse response = sdk.query(request);
		Assert.assertTrue(response.isSuccess());
	}
	
	@Test
	public void testClose() throws PaymentException {
		CloseOrderRequest request = new CloseOrderRequest();
		request.setOutTradeNo(outTradeNo);
		request.put("operator_id", "YX01");
		CloseOrderResponse response = sdk.close(request);
		Assert.assertTrue(response.isSuccess());
	}
	
	@Test
	public void testRefund() throws PaymentException {
		RefundRequest request = new RefundRequest();
		request.setOutTradeNo("20150320010101001");
		request.setTradeNo("2014112611001004680073956707");
		request.setRefundAmount("200.12");
		request.put("refund_reason", "正常退款");
		request.setOutRefundNo("HZ01RF001");
		RefundResponse response = sdk.refund(request);
		Assert.assertTrue(response.isSuccess());
	}
	
	@Test
	public void testRefundQuery() throws PaymentException {
		RefundQueryRequest request = new RefundQueryRequest();
		request.setOutTradeNo("2014112611001004680073956707");
		request.setTradeNo("20150320010101001");
		request.setOutRefundNo("2014112611001004680073956707");
		RefundQueryResponse response = sdk.refundQuery(request);
		Assert.assertTrue(response.isSuccess());
	}
	
	@Test
	public void testScanpay() throws PaymentException {
		ScanPayRequest request = new ScanPayRequest();
		request.setOutTradeNo("170327122101538152540");
		request.setAuthCode("281414349429022701");
		request.setSubject("Iphone6 16G");
		request.setBody("Iphone6 16G");
		request.setTotalAmount("88.88");
		request.setTerminalId("NJ_T_001");
		request.put("scene", "bar_code,wave_code");
		request.put("product_code", "FACE_TO_FACE_PAYMENT");
		
		ScanPayResponse response = sdk.scanpay(request);
		Assert.assertTrue(response.isSuccess());
	}
	
	@Test
	public void testCancle() throws PaymentException {
		CancleOrderRequest request = new CancleOrderRequest();
		request.setOutTradeNo("20150320010101001");
		CancleOrderResponse response = sdk.cancle(request);
		Assert.assertTrue(response.isSuccess());
	}
	
}
