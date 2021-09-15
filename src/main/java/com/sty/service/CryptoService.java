package com.sty.service;

import java.util.List;

import com.sty.entity.Crypto;

public interface CryptoService {

    List<Crypto> getAll();

    void create(Crypto crypto);

    void update(Crypto crypto);

    void delete(int cmcId);
}
