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
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class PDFBoxUtil {
    private final AppProperties appProperties;

    public void createPdf(String pdfName) throws IOException {
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

        // 关闭页面内容流
        contentStream.close();

        // 保存 PDF 文件
        String pdfFilePath = appProperties.getTmpDir() + File.separator + pdfName + ".pdf";
        document.save(new File(pdfFilePath));
        document.close();

        log.info("PDF 文件已创建成功！你可以在[{}]查看", pdfFilePath);
    }

    public void fillPdfWithVariables(String sourcePdfFilePath, String destinationPdfFilePath, Map<String, String> variableMap, String imagePath) throws IOException {
        try (PDDocument document = PDDocument.load(new File(sourcePdfFilePath))) {
            PDPage page = document.getPage(0); // 获取第一页

            // 获取页面尺寸
            PDRectangle pageSize = page.getMediaBox();

            // 创建页面内容流
            PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, true);

            // 设置字体和字号
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);


            float textX = pageSize.getWidth() - 500;
            float textY = pageSize.getHeight() - 300;

            int i = 1;
            // 填写变量信息到页面
            for (Map.Entry<String, String> entry : variableMap.entrySet()) {
                String variableName = entry.getKey();
                String variableValue = entry.getValue();
                contentStream.beginText();
                float y = textY - 30 * i;
                log.info("variableName:{}, variableValue:{}, x:{}, y:{}", variableName, variableValue, textX, y);
                contentStream.newLineAtOffset(textX, y); // 修改为合适的坐标
                contentStream.showText(variableValue);
                contentStream.endText();
                i++;
            }

            // 添加印章图像到页面
            PDImageXObject image = PDImageXObject.createFromFile(imagePath, document);
            float imageWidth = 30; // 图像宽度
            float imageHeight = 30; // 图像高度
            float imageX = pageSize.getWidth() - 100; // 图像 X 坐标
            float imageY = pageSize.getHeight() - 400; // 图像 Y 坐标
            contentStream.drawImage(image, imageX, imageY, imageWidth, imageHeight);
            // 关闭页面内容流
            contentStream.close();

            // 保存填充后的 PDF 文件
            document.save(new File(destinationPdfFilePath));
            log.info("PDF 文件已创建成功！{}", destinationPdfFilePath);
        }
    }

}
