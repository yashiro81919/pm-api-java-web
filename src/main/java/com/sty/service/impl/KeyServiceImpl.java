package com.sty.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sty.entity.Key;
import com.sty.mapper.KeyMapper;
import com.sty.service.KeyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyServiceImpl implements KeyService {

    @Autowired
    private KeyMapper keyMapper;

    @Override
    public Key get(String name) {
        return keyMapper.get(name);
    }

    @Override
    public void create(Key key) {
        keyMapper.insert(key);
    }

    @Override
    public List<Key> search(String name) {
        if (name.trim().isEmpty()) {
            return new ArrayList<Key>();
        }
        return keyMapper.search(name);
    }

    @Override
    public void update(Key key) {
        keyMapper.update(key);
    }

    @Override
    public void delete(String name) {
        keyMapper.delete(name);
    }
}
