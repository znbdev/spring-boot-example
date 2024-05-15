package com.example.demo.service;

import com.example.demo.properties.EnvProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class EnvServiceTest {

    @MockBean
    private EnvProperties envProperties;

    @Test
    void getEnvTest() {
        // 模拟属性值
        String expectedEnv = "test";
        when(envProperties.getEnv()).thenReturn(expectedEnv);

        // 创建被测试的服务对象
        EnvService envService = new EnvService(envProperties);

        // 调用服务方法并断言结果
        String actualEnv = envService.getEnv();
        assertEquals(expectedEnv, actualEnv);
    }
}