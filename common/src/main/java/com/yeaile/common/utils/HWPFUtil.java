package com.yeaile.common.utils;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author yefan
 * @Classname HWPFUtil
 * @Description TODO
 * @Date 2019/12/29 4:48 下午
 * @Created by SulaymanYf
 */
public class HWPFUtil {

    public static String wordExtractor(String filePath,String  suffix) throws IOException {
        XWPFDocument docx = new XWPFDocument(
                new FileInputStream(filePath));
        //using XWPFWordExtractor Class
        XWPFWordExtractor we = new XWPFWordExtractor(docx);
        StringBuffer sb = new StringBuffer();
        String buffer = "";
        try {
            if ("doc".equalsIgnoreCase(suffix)) {
                InputStream is = new FileInputStream(filePath);
                WordExtractor ex = new WordExtractor(is);
                buffer = ex.getText();
                if(buffer.length() > 0){
                    //使用回车换行符分割字符串
                    String [] arry = buffer.split("\\r\\n");
                    for (String string : arry) {
                        sb.append(string.trim());
                    }
                }
            } else if (".docx".equalsIgnoreCase(suffix)) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(filePath);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                buffer = extractor.getText();
                if(buffer.length() > 0){
                    //使用换行符分割字符串
                    String [] arry = buffer.split("\\n");
                    for (String string : arry) {
                        sb.append(string.trim());
                    }
                }
            } else {
                return null;
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.print("error---->"+filePath);
            e.printStackTrace();
            return null;
        }

    }

}
