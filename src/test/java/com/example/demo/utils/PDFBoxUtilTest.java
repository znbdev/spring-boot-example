package com.example.demo.utils;

import com.example.demo.properties.AppProperties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class PDFBoxUtilTest {
    @MockBean
    private AppProperties appProperties;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All");
    }


    @BeforeEach
    void setUp() {
        System.out.println("Before Each");
        // 模拟属性值
        String tmpDir = "tmp";
        when(appProperties.getTmpDir()).thenReturn(tmpDir);
    }


    @Test
    void createPdfTest() throws IOException {
        PDFBoxUtil pdfBoxUtil = new PDFBoxUtil(appProperties);
        pdfBoxUtil.createPdf();
        assertTrue(true);
    }
}