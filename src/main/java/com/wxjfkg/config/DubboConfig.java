package com.wxjfkg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath:applicationContext-provider.xml" })
public class DubboConfig {

}
