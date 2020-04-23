package com.ebs.platform.core.util;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;
import java.io.File;
/**
 * 不能使用中文水印
 * 没有使用的
 */
public class PDFBoxTest {

    private static void watermarkPDF (File fileStored) throws Exception {
        File tmpPDF;
        PDDocument doc;


        tmpPDF = new File(fileStored.getParent() + System.getProperty("file.separator") +"Tmp_"+fileStored.getName());
        doc = PDDocument.load(fileStored);
        doc.setAllSecurityToBeRemoved(true);
        for(PDPage page:doc.getPages()){
            PDPageContentStream cs = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true, true);
            String ts = "Some sample text";
            PDFont font = PDType1Font.HELVETICA_OBLIQUE;
            float fontSize = 50.0f;
            PDResources resources = page.getResources();
            PDExtendedGraphicsState r0 = new PDExtendedGraphicsState();
            // 透明度
            r0.setNonStrokingAlphaConstant(0.2f);
            r0.setAlphaSourceFlag(true);
            cs.setGraphicsStateParameters(r0);
            cs.setNonStrokingColor(200,0,0);//Red
            cs.beginText();
            cs.setFont(font, fontSize);
            // 获取旋转实例
            cs.setTextMatrix(Matrix.getRotateInstance(20,350f,490f));
            cs.showText(ts);
            cs.endText();

            cs.close();
        }
        doc.save(tmpPDF);
    }

}
