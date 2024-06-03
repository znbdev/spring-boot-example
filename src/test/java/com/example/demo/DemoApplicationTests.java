package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = {"info.env=test"})
@TestPropertySource(properties = "tmp.dir=tmp")
//@PropertySource("classpath:application-test.properties")
class DemoApplicationTests {

    @Value("${info.env}")
    private String env;
    @Value("${tmp.dir}")
    private String tmpDir;

    @Test
    void contextLoads() {
        System.out.println("UT env: " + env);
        System.out.println("UT tmpDir: " + tmpDir);
        assertEquals("test", env);
    }
}
