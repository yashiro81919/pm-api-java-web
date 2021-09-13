package com.sty.controller;

import java.util.List;

import com.sty.entity.Key;
import com.sty.service.KeyService;

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

    @Autowired
    private KeyService service;

    @GetMapping("/{name}")
    public Key get(@PathVariable String name) {
        return service.get(name);
    }

    @GetMapping("")
    public List<Key> search(@RequestParam String name) {
        return service.search(name);
    }

    @PostMapping("")
    public void create(@RequestBody Key key) {
        service.create(key);
    }

    @PutMapping("")
    public void update(@RequestBody Key key) {
        service.update(key);
    }

    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        service.delete(name);
    }
}
