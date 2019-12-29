package com.yeaile.web.word;

import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Classname wordText
 * @Description TODO
 * @Date 2019/12/29 5:43 下午
 * @Created by SulaymanYf
 */
@SpringBootTest
public class wordText {


    @Test
    public void wordExtractor() throws IOException {

        String filePath="/Users/yefan/IdeaProjects/cevirikizlar/tempfiles/2019-12-29-222.docx";
//        HWPFDocument docx = new HWPFDocument(new FileInputStream(filePath));

        XWPFDocument docx = new XWPFDocument(new FileInputStream(filePath));

        //using XWPFWordExtractor Class
        XWPFWordExtractor we = new XWPFWordExtractor(docx);
        POIXMLDocument document = we.getDocument();

//        InputStream is = new FileInputStream(filePath);
        String buffer = we.getText();
        StringBuffer sb = new StringBuffer();
        if(buffer.length() > 0){
            //使用回车换行符分割字符串
            String [] arry = buffer.split("\\r\\n");
            for (String string : arry) {
                sb.append(string.trim());
            }
        }
        System.out.println(sb.toString());
//        HWPFDocument doc = new HWPFDocument(is);

//        System.out.println(we.getText());
//        System.out.println(we.getCoreProperties());
//        System.out.println(we.getDocument());
//        String paraTexts[] = we.getParagraphText();

        //输出书签信息
//        System.out.println(doc.getBookmarks());
//        //输出文本
//        System.out.println(doc.getDocumentText());
//        Range range = doc.getRange();
//        //读整体
//        System.out.println(range);
//        //读表格
//        System.out.println(range);

    }

    @Test
    public void getword() throws IOException {
        String filePath="/Users/yefan/IdeaProjects/cevirikizlar/tempfiles/2019-12-29-222.docx";

        InputStream is = new FileInputStream(filePath);
        XWPFDocument docx = new XWPFDocument(new FileInputStream(filePath));
        XWPFWordExtractor we = new XWPFWordExtractor(docx);
        List<XWPFParagraph> r = docx.getParagraphs();
        for (XWPFParagraph xwpfParagraph : r) {
            System.out.println(xwpfParagraph.toString());
        }

    }

}
