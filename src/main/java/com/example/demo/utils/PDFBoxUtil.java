package com.example.demo.utils;

import com.example.demo.properties.AppProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class PDFBoxUtil {
    private final AppProperties appProperties;

    public void createPdf() throws IOException {
        // 创建 PDF 文档
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // 获取页面尺寸
        PDRectangle pageSize = page.getMediaBox();

        // 创建页面内容流
        PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, true);

        // 设置字体和字号
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 24);

        // 添加文本到页面
        contentStream.beginText();
        contentStream.newLineAtOffset(100, pageSize.getHeight() - 100);
        contentStream.showText("Hello, PDFBox!");
        contentStream.endText();

        // 添加印章图像到页面
        String imagePath = appProperties.getTmpDir() + File.separator + "zhang.png";
        PDImageXObject image = PDImageXObject.createFromFile(imagePath, document);
        float imageWidth = 30; // 图像宽度
        float imageHeight = 30; // 图像高度
        float imageX = pageSize.getWidth() - imageWidth - 100; // 图像 X 坐标
        float imageY = pageSize.getHeight() - imageHeight - 100; // 图像 Y 坐标
        contentStream.drawImage(image, imageX, imageY, imageWidth, imageHeight);

        // 关闭页面内容流
        contentStream.close();

        // 保存 PDF 文件
        String pdfFilePath = appProperties.getTmpDir() + File.separator + "pdfbox_example_with_seal.pdf";
        document.save(new File(pdfFilePath));
        document.close();

        log.info("PDF 文件已创建成功！你可以在[{}]查看", pdfFilePath);
    }


}
