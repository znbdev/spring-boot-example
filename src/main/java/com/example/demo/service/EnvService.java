package com.example.demo.service;

import com.example.demo.properties.EnvProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EnvService {
    private final EnvProperties envProperties;

    public String getEnv() {
        return envProperties.getEnv();
    }

}
