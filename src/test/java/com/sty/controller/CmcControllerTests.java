package com.sty.controller;

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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CmcController.class)
@AutoConfigureMybatis
public class CmcControllerTests {

    @Autowired
	private MockMvc mockMvc;

    @MockBean
    private KeyService service;    

    @MockBean
    private ApiKeyBean bean;      

    @Test
    public void returnDataFromCmc() throws Exception {
        Key key = new Key();
        key.setName("key");
        key.setKey("1");
        key.setValue("1");
        when(service.get("key")).thenReturn(key);

        mockMvc.perform(get("/api/cmcs")).andDo(print()).andExpect(status().isOk());
    }
}
