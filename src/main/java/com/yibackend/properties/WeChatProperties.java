package com.yibackend.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "config.wechat")
@Data
public class WeChatProperties {

    private String appid;
    private String secret;
}
