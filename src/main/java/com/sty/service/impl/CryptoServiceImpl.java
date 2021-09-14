package com.sty.service.impl;

import java.util.List;

import com.sty.entity.Crypto;
import com.sty.mapper.CryptoMapper;
import com.sty.service.CryptoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CryptoServiceImpl implements CryptoService {

    private static Logger logger = LoggerFactory.getLogger(CryptoServiceImpl.class);

    @Autowired
    private CryptoMapper cryptoMapper;

    @Override
    public List<Crypto> getAll() {
        logger.info("CryptoServiceImpl::getAll()");

        List<Crypto> list = cryptoMapper.getAll();
        logger.debug("Crypto list: {}", list);

        return list;
    }

    @Override
    public void create(Crypto crypto) {
        logger.info("CryptoServiceImpl::create()");
        logger.debug("Crypto object: {}", crypto);

        cryptoMapper.insert(crypto);
    }

    @Override
    public void update(Crypto crypto) {
        logger.info("CryptoServiceImpl::update()");
        logger.debug("Crypto object: {}", crypto);

        cryptoMapper.update(crypto);
    }

    @Override
    public void delete(String name) {
        logger.info("CryptoServiceImpl::delete()");
        logger.debug("Crypto name: {}", name);

        cryptoMapper.delete(name);
    }
}
