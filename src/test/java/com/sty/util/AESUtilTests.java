package com.sty.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AESUtilTests {
    
    @Test
    public void decryptTest() {
        String word = "U2FsdGVkX1+J+TmqbXrf9YF7ziKEhUj8K2RQp/cYUWQ=";

        String decrypted = AESUtil.decrypt(word);
        assertThat(decrypted).isEqualTo("hello world");
    }
}
