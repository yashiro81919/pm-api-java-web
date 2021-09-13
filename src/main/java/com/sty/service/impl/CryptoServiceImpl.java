package com.sty.service.impl;

import java.util.List;

import com.sty.entity.Crypto;
import com.sty.mapper.CryptoMapper;
import com.sty.service.CryptoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CryptoServiceImpl implements CryptoService {

    @Autowired
    private CryptoMapper cryptoMapper;

    @Override
    public List<Crypto> getAll() {
        return cryptoMapper.getAll();
    }

    @Override
    public void create(Crypto crypto) {
        cryptoMapper.insert(crypto);
    }

    @Override
    public void update(Crypto crypto) {
        cryptoMapper.update(crypto);
    }

    @Override
    public void delete(String name) {
        cryptoMapper.delete(name);
    }
}
