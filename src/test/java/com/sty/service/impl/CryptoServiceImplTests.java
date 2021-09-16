package com.sty.service.impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.sty.entity.Crypto;
import com.sty.mapper.CryptoMapper;
import com.sty.service.CryptoService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CryptoServiceImplTests {

    @Autowired
    private CryptoService service;

    @MockBean
    private CryptoMapper cryptoMapper;   

    @Test
    public void getAllTest() {
        List<Crypto> list = new ArrayList<>();
        // list.add(e);

        // when(cryptoMapper.getAll()).thenReturn(customer);

        // service.getAll();

        // assertThat(decrypted).isEqualTo("hello world");
    }
}
