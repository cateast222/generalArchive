package com.ebs.platform.core.util;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * Created by liujie on 2019/11/25.
 * ocr识别
 */
public class OcrUtil {

    private static ITesseract instance = new Tesseract();

    public static String ocrExecute(String url,String dataPath) throws TesseractException {
        //如果未将tessdata放在根目录下需要指定绝对路径
        //instance.setDatapath("E:\\wongxin\\tessdata");
        instance.setDatapath(dataPath);

        //如果需要识别英文之外的语种，需要指定识别语种，并且需要将对应的语言包放进项目中
        instance.setLanguage("chi_sim");
        String ocrResult = "";
        // 指定识别图片
        File file = new File(url);
        ocrResult = instance.doOCR(file);
       /* File[] files = file.listFiles();
        String ocrResult = "";
        for(int i =0;i<files.length;i++){
            if(!files[i].isDirectory()){
                ocrResult += instance.doOCR(files[i]);
            }
        }*/
        return ocrResult;
    }
}
