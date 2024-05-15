package com.example.demo.properties;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class EnvPropertiesTest {

    @Autowired
    private EnvProperties envProperties;

    @Test
    void testEnvProperty() {
        String env = envProperties.getEnv();
        log.info("envProperties: {}", env);
        // 验证属性注入是否正确
        assertEquals("test", env);
    }
}