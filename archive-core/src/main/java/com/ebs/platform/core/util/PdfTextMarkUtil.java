package com.ebs.platform.core.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 本系统-使用这个
 */
public class PdfTextMarkUtil {


    public static void addPdfTextMark(String InPdfFile, String outPdfFile, String textMark,int textWidth,  int textHeight)
            throws Exception {

    }
















    /**
     * Fixed 固定的水印
     * Description: 给pdf文件添加文字水印<br>
     * @param InPdfFile  要加水印的原pdf文件路径
     * @param outPdfFile 加了水印后要输出的路径
     * @param textMark   水印文字
     * @param textWidth  文字横坐标
     * @param textHeight 文字纵坐标
     * @throws Exception
     * @see
     */
    public static void addFixedPdfTextMark(String InPdfFile, String outPdfFile, String textMark,int textWidth,  int textHeight)
            throws Exception {
        PdfReader reader = new PdfReader(InPdfFile, "PDF".getBytes());
        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(new File(outPdfFile)));
        PdfContentByte under;// 主作用对象
        PdfGState gs = new PdfGState();//透明度用的
        gs.setFillOpacity(0.4f);//设置透明度为0.3
        // 需要设置字体，不然中文不支持
        BaseFont font = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",   BaseFont.EMBEDDED);
        int pageSize = reader.getNumberOfPages();// 原pdf文件的总页数 这里不用加 1 也是对的
        for(int i = 1; i <= pageSize; i++) {
            //under = stamp.getUnderContent(i);// 水印在之前文本下
            under = stamp.getOverContent(i);//水印在之前文本上
            under.setGState(gs);
            under.beginText();
            under.setColorFill(BaseColor.RED);// 文字水印 颜色
            under.setFontAndSize(font, 38);// 文字水印 字体及字号
            under.setTextMatrix(textWidth, textHeight);// 文字水印 起始位置
            under.showTextAligned(Element.ALIGN_CENTER, textMark, textWidth, textHeight, 45);
            under.endText();
        }
        //一定不要忘记关闭流
        stamp.close();// 注意先后顺序
        reader.close();
    }
//    public static void main(String[] args) {
//        try {
//            addPdfTextMark22("E:\\testpdf\\boo.pdf","E:\\testpdf\\boo2.pdf", "档案仅用于借阅", 300, 450);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
