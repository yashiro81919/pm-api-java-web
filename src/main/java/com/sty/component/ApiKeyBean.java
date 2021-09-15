package com.sty.component;

import javax.annotation.PostConstruct;

import com.sty.entity.Key;
import com.sty.service.KeyService;
import com.sty.util.AESUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ApiKeyBean {

    private static Logger logger = LoggerFactory.getLogger(ApiKeyBean.class);

    private String apiKey; 

    @Autowired
    private KeyService service;
    
    @PostConstruct
    public void postConstruct() {
        logger.info("fetch API key from database");

        Key key = service.get("key");
        if (key == null) {
            logger.error("cannot find key in database or issue happen");
            return;
        }
        apiKey = AESUtil.decrypt(key.getKey()) + AESUtil.decrypt(key.getValue());

        logger.debug("apiKey: {}", apiKey);
    }

    public String getApiKey() {
        return apiKey;
    }
}
