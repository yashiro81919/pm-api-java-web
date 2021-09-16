package com.sty.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.sty.entity.Key;
import com.sty.mapper.KeyMapper;
import com.sty.service.KeyService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class KeyServiceImplTests {
    
    @Autowired
    private KeyService service;

    @MockBean
    private KeyMapper keyMapper;
    
    @Test
    public void getTest() {
        Key key = new Key();
        key.setName("test");
        key.setKey("test");
        key.setName("test");

        when(keyMapper.get("test")).thenReturn(key);

        Key newKey = service.get("test");

        assertNotNull(newKey);
        verify(keyMapper, times(1)).get("test");
    }
    
    @Test
    public void createTest() {
        Key key = new Key();
        key.setName("test");
        key.setKey("test");
        key.setName("test");

        service.create(key);

        verify(keyMapper, times(1)).insert(key);
    } 
    
    @Test
    public void updateTest() {
        Key key = new Key();
        key.setName("test");
        key.setKey("test");
        key.setName("test");

        service.update(key);

        verify(keyMapper, times(1)).update(key);
    } 
    
    @Test
    public void deleteTest() {
        String name = "test";

        service.delete(name);

        verify(keyMapper, times(1)).delete(name);
    }   
    
        
    @Test
    public void searchWithEmptyNameTest() {
        String name = "";

        List<Key> list = service.search(name);

        assertThat(list.size()).isEqualTo(0);
    } 

    @Test
    public void searchWithNonEmptyNameTest() {
        String name = "te";

        List<Key> list = new ArrayList<>();
        Key key = new Key();
        key.setName("test");
        key.setKey("test");
        key.setName("test");
        list.add(key);

        when(keyMapper.search(name)).thenReturn(list);

        List<Key> searchList = service.search(name);

        assertThat(searchList.size()).isGreaterThan(0);
        verify(keyMapper, times(1)).search(name);
    }     
}
