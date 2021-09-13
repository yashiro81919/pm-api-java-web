package com.sty.service;

import com.sty.entity.Key;

import java.util.List;

public interface KeyService {

    Key get(String name);

    List<Key> search(String name);

    void create(Key key);

    void update(Key key);

    void delete(String name);
}
