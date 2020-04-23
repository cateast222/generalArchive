package com.ebs.platform.core.util;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.util.Matrix;

/**
 * 不能使用中文水印
 * 没有使用的
 */
public class PdfBoxText {

//    public static void main(String[] args) throws InvalidPasswordException, IOException {
//        readString("E:\\testpdf\\boo.pdf");
//    }

    /**
     * 读取文本
     */
    public static void readString(String source) throws IOException{
        PDDocument doc = PDDocument.load(new File(source));
        PDFTextStripper stripper = new PDFTextStripper();
        // 设置按顺序输出
//        stripper.setSortByPosition(true);
//        stripper.setStartPage(1);
//        stripper.setEndPage(doc.getNumberOfPages());
//        String text = stripper.getText(doc);
        markTxt("E:\\testpdf\\boo.pdf","E:\\testpdf\\boo3.pdf");
       // System.out.println(text);
    }

    /**
     * 水印
     */
    public static void markTxt(String source, String target) throws InvalidPasswordException, IOException {
        File tmpPDF = new File(target);
        PDDocument doc = PDDocument.load(new File(source));
        doc.setAllSecurityToBeRemoved(true);
        for (PDPage page : doc.getPages()) {
            PDPageContentStream cs = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true,
                    true);

            String ts = "Read By Jack";
            PDFont font = PDType1Font.HELVETICA_OBLIQUE;
            // PDResources resources = page.getResources();
            PDExtendedGraphicsState r0 = new PDExtendedGraphicsState();
            // 透明度
            r0.setNonStrokingAlphaConstant(0.7f);
            r0.setAlphaSourceFlag(true);
            cs.setGraphicsStateParameters(r0);
            cs.setNonStrokingColor(200, 0, 0);// Red
            cs.beginText();
            float fontSize = 50.0f;
            cs.setFont(font, fontSize);
            // 获取旋转实例
            cs.setTextMatrix(Matrix.getRotateInstance(30, 250f, 390f));
            cs.showText(ts);
            cs.endText();

            cs.close();
        }
        doc.save(tmpPDF);
    }
}
