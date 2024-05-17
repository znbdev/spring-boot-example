package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = {"info.env=test"})
class DemoApplicationTests {

    @Value("${info.env}")
    private String env;

    @Test
    void contextLoads() throws Exception {
        System.out.println("UT env: " + env);
        assertEquals("test", env);
    }
}
