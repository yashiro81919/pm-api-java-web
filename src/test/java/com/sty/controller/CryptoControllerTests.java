package com.sty.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sty.component.ApiKeyBean;
import com.sty.entity.Crypto;
import com.sty.service.CryptoService;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CryptoController.class)
@AutoConfigureMybatis
@AutoConfigureMockMvc(addFilters = false)
public class CryptoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CryptoService service;

    @MockBean
    private ApiKeyBean bean;

    @Test
    public void getAllTest() throws Exception {
        List<Crypto> list = new ArrayList<>();

        Crypto crypto = new Crypto();
        crypto.setCmcId(1234);
        crypto.setQuantity(1000);
        crypto.setRemark("remark");
        list.add(crypto);
        when(service.getAll()).thenReturn(list);

        mockMvc.perform(get("/api/cryptos")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cmcId").value("1234"));
    }

    @Test
    public void createTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Crypto crypto = new Crypto();
        crypto.setCmcId(1234);
        crypto.setQuantity(1000);
        crypto.setRemark("remark");

        String jsonString = mapper.writeValueAsString(crypto);

        mockMvc.perform(post("/api/cryptos").content(jsonString).contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());

        verify(service, times(1)).create(any());
    }

    @Test
    public void updateTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Crypto crypto = new Crypto();
        crypto.setCmcId(1234);
        crypto.setQuantity(1000);
        crypto.setRemark("remark");

        String jsonString = mapper.writeValueAsString(crypto);

        mockMvc.perform(put("/api/cryptos").content(jsonString).contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());

        verify(service, times(1)).update(any());
    }

    @Test
    public void deleteTest() throws Exception {

        mockMvc.perform(delete("/api/cryptos/1234")).andDo(print()).andExpect(status().isOk());

        verify(service, times(1)).delete(1234);
    }
}
