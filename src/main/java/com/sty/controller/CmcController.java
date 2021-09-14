package com.sty.controller;

import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.sty.entity.Key;
import com.sty.service.KeyService;
import com.sty.util.AESUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/cmcs")
public class CmcController {

    private static Logger logger = LoggerFactory.getLogger(CmcController.class);

    @Autowired
    private KeyService service;    

    @GetMapping("")
    public JsonNode getAll() {
        logger.info("CmcController::getAll()");

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("start", "1")
                .queryParam("limit", "200").queryParam("convert", "USD");

        Key cmcKey = service.get("cmc-key");

        HttpHeaders headers = new HttpHeaders();
        headers.set(AESUtil.decrypt(cmcKey.getKey()), AESUtil.decrypt(cmcKey.getValue()));
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<JsonNode> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, JsonNode.class);

        logger.debug("response from CMC API: {}", response);

        return response.getBody().get("data");
    }
}
