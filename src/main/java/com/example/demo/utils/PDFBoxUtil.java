package com.example.demo.utils;

import com.example.demo.properties.AppProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class PDFBoxUtil {
    private final AppProperties appProperties;

    public void createPdf() throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(100, 700);
        contentStream.showText("Hello, PDFBox!");
        contentStream.endText();
        contentStream.close();

        String pdfFileDir = appProperties.getTmpDir() + "/pdfbox_example.pdf";
        document.save(new File(pdfFileDir));
        document.close();
        log.info("PDF 文件已创建成功！{}", pdfFileDir);
    }
}
