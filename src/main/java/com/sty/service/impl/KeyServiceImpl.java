package com.sty.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sty.entity.Key;
import com.sty.mapper.KeyMapper;
import com.sty.service.KeyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyServiceImpl implements KeyService {

    private static Logger logger = LoggerFactory.getLogger(KeyServiceImpl.class);

    @Autowired
    private KeyMapper keyMapper;

    @Override
    public Key get(String name) {
        logger.info("KeyServiceImpl::get()");

        Key key = keyMapper.get(name);
        logger.debug("Key object: {}", key);

        return key;
    }

    @Override
    public void create(Key key) {
        logger.info("KeyServiceImpl::create()");
        logger.debug("Key object: {}", key);

        keyMapper.insert(key);
    }

    @Override
    public List<Key> search(String name) {
        logger.info("KeyServiceImpl::search()");

        if (name.trim().isEmpty()) {
            logger.debug("empty Key list");
            return new ArrayList<Key>();
        }

        List<Key> list = keyMapper.search(name);
        logger.debug("Key list: {}", list);

        return list;
    }

    @Override
    public void update(Key key) {
        logger.info("KeyServiceImpl::update()");
        logger.debug("Key object: {}", key);

        keyMapper.update(key);
    }

    @Override
    public void delete(String name) {
        logger.info("KeyServiceImpl::delete()");
        logger.debug("Key name: {}", name);

        keyMapper.delete(name);
    }
}
