package com.example.demo.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppProperties {
    @Value("${info.env}")
    private String env;
    @Value("${tmp.dir}")
    private String tmpDir;
}
