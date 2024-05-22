package com.example.demo.service;

import com.example.demo.properties.AppProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class AppServiceTest {

    @MockBean
    private AppProperties appProperties;

    @Test
    void getEnvTest() {
        // 模拟属性值
        String expectedEnv = "test";
        when(appProperties.getEnv()).thenReturn(expectedEnv);

        // 创建被测试的服务对象
        EnvService envService = new EnvService(appProperties);

        // 调用服务方法并断言结果
        String actualEnv = envService.getEnv();
        assertEquals(expectedEnv, actualEnv);
    }
}