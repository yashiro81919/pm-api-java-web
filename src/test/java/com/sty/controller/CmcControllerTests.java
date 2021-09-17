package com.sty.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sty.component.ApiKeyBean;
import com.sty.entity.Key;
import com.sty.service.KeyService;
import com.sty.util.AESUtil;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
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
        key.setKey("X-CMC_PRO_API_KEY");
        key.setValue("1");
        when(service.get("cmc-key")).thenReturn(key);

        MockedStatic<AESUtil> utilities = Mockito.mockStatic(AESUtil.class);
        utilities.when(() -> AESUtil.decrypt("1")).thenReturn("1");
        utilities.when(() -> AESUtil.decrypt("X-CMC_PRO_API_KEY")).thenReturn("X-CMC_PRO_API_KEY");

        mockMvc.perform(get("/api/cmcs")).andDo(print()).andExpect(status().isBadRequest()).andExpect(result -> {
            Exception e = result.getResolvedException();
        });
    }
}
