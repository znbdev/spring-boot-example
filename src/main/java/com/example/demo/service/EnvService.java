package com.example.demo.service;

import com.example.demo.properties.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EnvService {
    private final AppProperties appProperties;

    public String getEnv() {
        return appProperties.getEnv();
    }

}
