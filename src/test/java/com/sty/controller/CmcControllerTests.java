package com.sty.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sty.component.ApiKeyBean;
import com.sty.entity.Key;
import com.sty.service.KeyService;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CmcController.class)
@AutoConfigureMybatis
@AutoConfigureMockMvc(addFilters = false)
public class CmcControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KeyService service;

    @MockBean
    private ApiKeyBean bean;

    @Test
    public void returnDataFromCmcTest() throws Exception {
        Key key = new Key();
        key.setName("cmc-key");
        key.setKey("U2FsdGVkX19ivYmkSzT9+vWfekkO+b8+891zuAWBLo8Z8tb9pSXvX2xso6uohxHm");
        key.setValue("U2FsdGVkX1+J+TmqbXrf9YF7ziKEhUj8K2RQp/cYUWQ=");
        when(service.get("cmc-key")).thenReturn(key);

        mockMvc.perform(get("/api/cmcs")).andDo(print()).andExpect(status().is4xxClientError()).andExpect(status().reason("Token is invalid"));

        verify(service, times(1)).get("cmc-key");
    }
}
