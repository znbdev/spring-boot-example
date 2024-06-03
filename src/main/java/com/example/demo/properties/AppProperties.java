package com.example.demo.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppProperties {
    @Value("${info.env:local}")
    private String env;
    @Value("${tmp.dir:tmp}")
    private String tmpDir;
}
