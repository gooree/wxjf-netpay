package com.wxjfkg.payment.utils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 通过RestTemplate Post方式调用外系统接口
 * Created by zhangjinye on 16/10/10.
 */
@Component
public class HttpPost {

    private static final Logger LOG = LoggerFactory.getLogger(HttpPost.class);

    //设置字符集为UTF-8
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 构建RestTemplate给spring容器管理
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
    	List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();
    	list.add(stringHttpMessageConverter());
        RestTemplate restTemplate = new RestTemplate(list);
        return restTemplate;
    }

    /**
     * 构建ClientHttpRequestFactory
     * 设置相关属性 比如读 连接超时等
     * @return
     */
    @Bean
    public ClientHttpRequestFactory factory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //读超时
        factory.setReadTimeout(15000);
        //连接超时
        factory.setConnectTimeout(5000);
        return factory;
    }

    /**
     * 构建StringHttpMessageConverter
     * 当string进行交互时 设置字符属性为UTF-8
     * @return
     */
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        //设置字符集为UTF-8
        StringHttpMessageConverter converter = new StringHttpMessageConverter(DEFAULT_CHARSET);
        //设置text/plain为UTF-8
        final Map<String, String> parameterMap = new HashMap<String, String>(4);
        parameterMap.put("charset", DEFAULT_CHARSET.name());
        MediaType mediaType = new MediaType("text", "plain", parameterMap);
        List<MediaType> mediaTypeList = new ArrayList<MediaType>();
        mediaTypeList.add(mediaType);
        converter.setSupportedMediaTypes(mediaTypeList);
        return converter;
    }

    /**
     * POST方式请求外系统
     *
     * @param requestBody 请求参数
     * @param url  请求地址
     * @return
     */
    public String post(String requestBody, String url, Map<String,String> parameters) {
    	if(parameters != null && !parameters.isEmpty()){
	    	String urlArgs = toUrl(parameters);
	    	if(StringUtils.isNotBlank(urlArgs)){
	    		StringBuffer sb = new StringBuffer(url);
	    		sb.append("?");
	    		sb.append(urlArgs);
	    		url = sb.toString();
	    	}
    	}
    	long reqNo = System.currentTimeMillis();	
    	LOG.info("Request[{}]->请求内容为:{},请求地址为:{}",reqNo,requestBody,url);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestBody, String.class);
        if (responseEntity != null) {
            String body = responseEntity.getBody();
            LOG.info("Response[{}]->请求返回:{}", reqNo, body);
            return body;
        }
        LOG.info("Request[{}]->请求返回null", reqNo);
        return null;
    }
    
    private String toUrl(Map<String, String> map){
    	if(map == null || map.isEmpty()){
    		return "";
    	}
    	StringBuffer url = new StringBuffer();
    	Iterator<Entry<String, String>> itr = map.entrySet().iterator();
    	while(itr.hasNext()){
    		Entry<String, String> entry = itr.next();
    		String key = entry.getKey();
    		Object value = entry.getValue();
    		url.append("&").append(key).append("=").append(value);
    	}
    	return url.length() > 1?url.substring(1):"";
    }

}
