package com.ebs.platform.core.util;

import java.awt.FontMetrics;
import java.io.FileOutputStream;
import javax.swing.JLabel;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
// 文字铺满PDF
// 没有使用的
public class PdfWaterMarkTest {

    private static int interval = -5;
    public static void waterMark(String inputFile,
                                 String outputFile, String waterMarkName) {
        try {
            PdfReader reader = new PdfReader(inputFile);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(
                    outputFile));

            //设置字体 这里支持中文就要这样设置
            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",   BaseFont.EMBEDDED);

            Rectangle pageRect = null;
            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.3f);//设置透明度为0.3
            gs.setStrokeOpacity(0.4f);//设置描边的透明度
            int total = reader.getNumberOfPages() + 1; // 这里加不加一好像没区别

            // 下面这一段就是为了获取 这段水印文字的 高度宽度
            JLabel label = new JLabel();
            FontMetrics metrics;
            int textH = 0;
            int textW = 0;
            label.setText(waterMarkName);
            metrics = label.getFontMetrics(label.getFont());
            textH = metrics.getHeight();
            textW = metrics.stringWidth(label.getText());

            PdfContentByte under;
            for (int i = 1; i < total; i++) {
                pageRect = reader.getPageSizeWithRotation(i);//获取PDF文档的位置 例如 500*800
                under = stamper.getOverContent(i); //水印浮于PDF文档之上
                under.saveState(); //不知道作用， 加不加没看出来影响
                under.setGState(gs);// 透明度那些设置进来
                under.beginText();
                under.setFontAndSize(base, 20);

                // 水印文字成30度角倾斜
                //你可以随心所欲的改你自己想要的角度
                for (int height = interval + textH; height < pageRect.getHeight(); height = height + textH*10) { //textH*10 作用就是改变每行多少个

                    for (int width = interval + textW; width < pageRect.getWidth() + textW;  width = width + textW*2) {

                        under.showTextAligned(Element.ALIGN_LEFT  , waterMarkName, width - textW, height - textH, 30);
                    }
                }
                // 添加水印文字
                under.endText();
            }
            //说三遍
            //一定不要忘记关闭流
            //一定不要忘记关闭流
            //一定不要忘记关闭流
            stamper.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        waterMark("E:\\testpdf\\boo.pdf", "E:\\testpdf\\boo2.pdf", "档案仅用于借阅");
//    }
}
