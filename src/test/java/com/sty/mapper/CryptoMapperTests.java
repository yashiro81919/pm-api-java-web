package com.sty.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import com.sty.entity.Crypto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CryptoMapperTests {

    @Autowired
    private CryptoMapper cryptoMapper;

    @Test
    public void insertCryptoTest() {
        int cmcId = 5566;
        int quantity = 10000;
        String remark = "remark";

        Crypto crypto = new Crypto();
        crypto.setCmcId(cmcId);
        crypto.setQuantity(quantity);
        crypto.setRemark(remark);

        cryptoMapper.insert(crypto);

        Crypto newCrypto = cryptoMapper.get(cmcId);
        assertThat(newCrypto.getQuantity()).isEqualTo(quantity);
        assertThat(newCrypto.getRemark()).isEqualTo(remark);
    }

    @Test
    public void updateCryptoTest() {
        int cmcId = 1;
        int quantity = 5000;
        String remark = "remark1";

        Crypto crypto = new Crypto();
        crypto.setCmcId(cmcId);
        crypto.setQuantity(quantity);
        crypto.setRemark(remark);

        cryptoMapper.update(crypto);

        Crypto newCrypto = cryptoMapper.get(cmcId);
        assertNotNull(newCrypto);
        assertThat(newCrypto.getQuantity()).isEqualTo(quantity);
        assertThat(newCrypto.getRemark()).isEqualTo(remark);
    }

    @Test
    public void getAllCryptosTest() {
        List<Crypto> list = cryptoMapper.getAll();
        assertThat(list.size()).isGreaterThan(0);
    }

    @Test
    public void deleteCryptoTest() {
        int cmcId = 1;

        cryptoMapper.delete(cmcId);

        Crypto newCrypto = cryptoMapper.get(cmcId);
        assertNull(newCrypto);
    }
}