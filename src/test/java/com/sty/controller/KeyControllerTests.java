package com.sty.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sty.component.ApiKeyBean;
import com.sty.entity.Key;
import com.sty.service.KeyService;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(KeyController.class)
@AutoConfigureMybatis
@AutoConfigureMockMvc(addFilters = false)
public class KeyControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KeyService service;

    @MockBean
    private ApiKeyBean bean;

    @Test
    public void getTest() throws Exception {

        Key key = new Key();
        key.setName("test");
        key.setKey("test");
        key.setName("test");

        when(service.get("test")).thenReturn(key);

        mockMvc.perform(get("/api/keys/test")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("test"));
    }    

    @Test
    public void searchTest() throws Exception {
        List<Key> list = new ArrayList<>();

        Key key = new Key();
        key.setName("test");
        key.setKey("test");
        key.setName("test");
        list.add(key);
        when(service.search("te")).thenReturn(list);

        mockMvc.perform(get("/api/keys?name=te")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("test"));
    }

    @Test
    public void createTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Key key = new Key();
        key.setName("test");
        key.setKey("test");
        key.setName("test");

        String jsonString = mapper.writeValueAsString(key);

        mockMvc.perform(post("/api/keys").content(jsonString).contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());

        verify(service, times(1)).create(any());
    }

    @Test
    public void updateTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Key key = new Key();
        key.setName("test");
        key.setKey("test");
        key.setName("test");

        String jsonString = mapper.writeValueAsString(key);

        mockMvc.perform(put("/api/keys").content(jsonString).contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());

        verify(service, times(1)).update(any());
    }

    @Test
    public void deleteTest() throws Exception {

        mockMvc.perform(delete("/api/keys/test")).andDo(print()).andExpect(status().isOk());

        verify(service, times(1)).delete("test");
    }
}
