package com.sty.controller;

import java.util.List;

import com.sty.entity.Key;
import com.sty.service.KeyService;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/keys")
public class KeyController {

    private static Logger logger = LoggerFactory.getLogger(KeyController.class);

    @Autowired
    private KeyService service;

    @GetMapping("/{name}")
    public Key get(@PathVariable String name) {
        logger.info("KeyController::get()");

        return service.get(name);
    }

    @GetMapping("")
    public List<Key> search(@RequestParam String name) {
        logger.info("KeyController::search()");

        return service.search(name);
    }

    @PostMapping("")
    public void create(@RequestBody Key key) {
        logger.info("KeyController::create()");

        service.create(key);
    }

    @PutMapping("")
    public void update(@RequestBody Key key) {
        logger.info("KeyController::update()");

        service.update(key);
    }

    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        logger.info("KeyController::delete()");

        service.delete(name);
    }
}
