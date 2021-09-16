package com.sty.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.sty.entity.Crypto;
import com.sty.mapper.CryptoMapper;
import com.sty.service.CryptoService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CryptoServiceImplTests {

    @Autowired
    private CryptoService service;

    @MockBean
    private CryptoMapper cryptoMapper;   

    @Test
    public void getAllTest() {
        List<Crypto> list = new ArrayList<>();

        Crypto crypto = new Crypto();
        crypto.setCmcId(1234);
        crypto.setQuantity(1000);
        crypto.setRemark("remark");
        list.add(crypto);

        when(cryptoMapper.getAll()).thenReturn(list);

        List<Crypto> newList = service.getAll();

        assertThat(newList.size()).isGreaterThan(0);
        verify(cryptoMapper, times(1)).getAll();
    }

    @Test
    public void createTest() {
        Crypto crypto = new Crypto();
        crypto.setCmcId(1234);
        crypto.setQuantity(1000);
        crypto.setRemark("remark");

        service.create(crypto);

        verify(cryptoMapper, times(1)).insert(crypto);
    } 
    
    @Test
    public void updateTest() {
        Crypto crypto = new Crypto();
        crypto.setCmcId(1234);
        crypto.setQuantity(1000);
        crypto.setRemark("remark");

        service.update(crypto);

        verify(cryptoMapper, times(1)).update(crypto);
    } 
    
    @Test
    public void deleteTest() {
        int cmcId = 1234;

        service.delete(cmcId);

        verify(cryptoMapper, times(1)).delete(cmcId);
    }    
}
