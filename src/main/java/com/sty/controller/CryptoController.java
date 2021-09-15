package com.sty.controller;

import java.util.List;

import com.sty.entity.Crypto;
import com.sty.service.CryptoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cryptos")
public class CryptoController {

    private static Logger logger = LoggerFactory.getLogger(CryptoController.class);

    @Autowired
    private CryptoService service;

    @GetMapping("")
    public List<Crypto> getAll() {
        logger.info("CryptoController::getAll()");

        return service.getAll();
    }

    @PostMapping("")
    public void create(@RequestBody Crypto crypto) {
        logger.info("CryptoController::create()");

        service.create(crypto);
    }

    @PutMapping("")
    public void update(@RequestBody Crypto crypto) {
        logger.info("CryptoController::update()");

        service.update(crypto);
    }

    @DeleteMapping("/{cmcId}")
    public void delete(@PathVariable int cmcId) {
        logger.info("CryptoController::delete()");

        service.delete(cmcId);
    }
}
