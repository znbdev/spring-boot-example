package com.example.demo.utils;

import com.example.demo.properties.AppProperties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;
import java.io.IOException;
import java.util.Map;

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
        pdfBoxUtil.createPdf("test");
        assertTrue(true);
    }

    @Test
    void fillPdfWithVariablesTest() throws IOException {
        String pdfFilePath = appProperties.getTmpDir() + File.separator + "test" + ".pdf";
        String destinationPdfFilePath = appProperties.getTmpDir() + File.separator + "test2.pdf";
        Map<String, String> variableMap = Map.of("name", "nnn", "age", "25", "address", "aaa");
        String imagePath = appProperties.getTmpDir() + File.separator + "zhang.png";

        PDFBoxUtil pdfBoxUtil = new PDFBoxUtil(appProperties);
        pdfBoxUtil.fillPdfWithVariables(pdfFilePath, destinationPdfFilePath, variableMap, imagePath);
        assertTrue(true);
    }
}