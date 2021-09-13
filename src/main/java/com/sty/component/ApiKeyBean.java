package com.sty.component;

import javax.annotation.PostConstruct;

import com.sty.entity.Key;
import com.sty.service.KeyService;
import com.sty.util.AESUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ApiKeyBean {

    private String apiKey; 

    @Autowired
    private KeyService service;
    
    @PostConstruct
    public void postConstruct() {
        Key key = service.get("key");
        apiKey = AESUtil.decrypt(key.getKey()) + AESUtil.decrypt(key.getValue());
    }

    public String getApiKey() {
        return apiKey;
    }
}
