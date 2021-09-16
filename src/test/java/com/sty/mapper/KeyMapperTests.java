package com.sty.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import com.sty.entity.Key;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class KeyMapperTests {
    
    @Autowired
    private KeyMapper keyMapper;

    @Test
    public void insertKeyTest() {
        String name = "test";
        String key1 = "key";
        String value = "value";

        Key key = new Key();
        key.setName(name);
        key.setKey(key1);
        key.setValue(value);

        keyMapper.insert(key);

        Key newKey = keyMapper.get(name);
        assertThat(newKey.getName()).isEqualTo(name);
        assertThat(newKey.getKey()).isEqualTo(key1);
        assertThat(newKey.getValue()).isEqualTo(value);
    }

    @Test
    public void updateKeyTest() {
        String name = "key";
        String key1 = "2222";
        String value = "3333";

        Key key = new Key();
        key.setName(name);
        key.setKey(key1);
        key.setValue(value);

        keyMapper.update(key);

        Key newKey = keyMapper.get(name);
        assertNotNull(newKey);
        assertThat(newKey.getName()).isEqualTo(name);
        assertThat(newKey.getKey()).isEqualTo(key1);
        assertThat(newKey.getValue()).isEqualTo(value);
    }

    @Test
    public void searchKeysTest() {
        List<Key> list = keyMapper.search("_");
        assertThat(list.size()).isGreaterThan(0);
    }

    @Test
    public void deleteKeyTest() {
        String name = "key";

        keyMapper.delete(name);

        Key newKey = keyMapper.get(name);
        assertNull(newKey);
    }    
}
