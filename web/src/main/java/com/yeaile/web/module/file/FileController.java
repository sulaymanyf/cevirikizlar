package com.yeaile.web.module.file;
import com.google.common.base.Charsets;
import com.yeaile.ceviri.service.ICeviriService;
import com.yeaile.ceviri.service.IMetinService;
import com.yeaile.common.domain.ceviri.vo.CeviriVO;
import com.yeaile.common.domain.ceviri.vo.MetinVO;
import com.yeaile.common.result.Result;
import com.yeaile.common.result.ResultCode;
import com.yeaile.common.result.StatusCode;
import com.yeaile.common.utils.HWPFUtil;
import com.yeaile.file.service.IFileService;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.model.XWPFCommentsDecorator;
import org.apache.poi.xwpf.model.XWPFParagraphDecorator;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.InflaterInputStream;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-28
 */

@RestController
@RequestMapping("/api/ceviri-kizlar/")
public class FileController {

    @Resource
    private IFileService fileService;

    @Resource
    private IMetinService iMetinService;

    @Resource
    private ICeviriService iCeviriService;

    static boolean save =true;
    static boolean  nestLists = true;

    @PostMapping(value = "v1/fileUpload")
    public Result fileUpload(HttpServletRequest request , @RequestParam(value = "file") MultipartFile file) throws IOException {

        request.setCharacterEncoding("UTF-8");
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        // 文件名
        String fileName = file.getOriginalFilename();
        // 后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 上传后的路径
        // 新文件名
        if (!"pdf".equalsIgnoreCase(suffixName)|| !"docx".equalsIgnoreCase(suffixName)|| !"text".equalsIgnoreCase(suffixName)){
            //抛异常
        }
        String path = null;
        try {
            path = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String projectPath = path.substring(0, path.indexOf("web"));
        System.out.println(projectPath);
        String filePath = projectPath + "/tempfiles/";
        fileName = LocalDateTime.now().toLocalDate() + "-" + fileName;
        File newFile = new File(filePath + fileName);
        if (!newFile.getParentFile().exists()){
            newFile.getParentFile().mkdir();
        }
        file.transferTo(newFile);

        String newFilePath = "/tempfiles/" + fileName;
        String fileId = fileService.addFile(fileName,suffixName,newFilePath);
        return new Result(true, StatusCode.OK, fileId);
    }

    @GetMapping(value = "v1/file/{id}")
    public void getFile(HttpServletResponse response , @PathVariable(value = "id" ,required = false) String id) throws IOException, Docx4JException {
        String path = ResourceUtils.getURL("classpath:").getPath();
        String projectPath = path.substring(0, path.indexOf("web"));
        String filePath = fileService.getPathById(id);

        String docx1 = HWPFUtil.wordExtractor(projectPath + filePath, "docx");
//
//        WordprocessingMLPackage wordprocessingMLPackage = WordprocessingMLPackage.load(new File(projectPath+filePath));
        ServletOutputStream outputStream = response.getOutputStream();
//        wordprocessingMLPackage.save(outputStream);
//
//        FileCopyUtils.copy(new FileInputStream(projectPath+filePath),response.getOutputStream());

        XWPFDocument docx = new XWPFDocument(new FileInputStream(projectPath+filePath));

        XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(docx);
//        docx.write(outputStream);
        String text = xwpfWordExtractor.getText();
//        FileCopyUtils.copy(new ByteArrayInputStream(text.getBytes()), response.getOutputStream());
        // 转html
        WordprocessingMLPackage wordprocessingMLPackage = WordprocessingMLPackage.load(new File(projectPath+filePath));
        HTMLSettings htmlSettings = Docx4J.createHTMLSettings();
//        htmlSettings.setImageDirPath(projectPath+filePath + "_files");
//        htmlSettings.setImageTargetUri(projectPath+filePath.substring((projectPath+filePath).lastIndexOf("/") + 1) + "_files");
        htmlSettings.setWmlPackage(wordprocessingMLPackage);

        String userCSS = null;
        if (nestLists) {
            userCSS = "html, body, div, span, h1, h2, h3, h4, h5, h6, p, a, img,  table, caption, tbody, tfoot, thead, tr, th, td "
                    + "{ margin: 0; padding: 0; border: 0;}" + "body {line-height: 1;} ";
        } else {
            userCSS = "html, body, div, span, h1, h2, h3, h4, h5, h6, p, a, img,  ol, ul, li, table, caption, tbody, tfoot, thead, tr, th, td "
                    + "{ margin: 0; padding: 0; border: 0;}" + "body {line-height: 1;} ";

        }
        htmlSettings.setUserCSS(userCSS);

        OutputStream os;
        if (save) {
            os = new FileOutputStream(projectPath+filePath + ".html");
        } else {
            os = new ByteArrayOutputStream();
        }

        Docx4jProperties.setProperty("docx4j.Convert.Out.HTML.OutputMethodXML", true);

        Docx4J.toHTML(htmlSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);

        if (save) {
            System.out.println("Saved: " + projectPath+filePath + ".html ");
            FileCopyUtils.copy(new FileInputStream(projectPath+filePath + ".html"), response.getOutputStream());
        } else {
            System.out.println(((ByteArrayOutputStream) os).toString());
            FileCopyUtils.copy(new FileInputStream(projectPath+filePath + ".html"), response.getOutputStream());

        }

        if (wordprocessingMLPackage.getMainDocumentPart().getFontTablePart() != null) {
            wordprocessingMLPackage.getMainDocumentPart().getFontTablePart().deleteEmbeddedFontTempFiles();
        }
        htmlSettings = null;
        wordprocessingMLPackage = null;
    }

    @GetMapping(value = "v1/read-file/{id}")
    public void readFile(HttpServletResponse response , @PathVariable(value = "id" ,required = false) String id) throws IOException, Docx4JException {
        String path = ResourceUtils.getURL("classpath:").getPath();
        String projectPath = path.substring(0, path.indexOf("web"));
        MetinVO metinVO = iMetinService.metin(id);
        String filePath = fileService.getPathById(metinVO.getContent());
        FileCopyUtils.copy(new FileInputStream(projectPath+filePath),response.getOutputStream());
    }


    @GetMapping(value = "v1/file-ceviri/{id}")
    public Result readFile( @PathVariable(value = "id" ,required = false) String id) throws IOException {
        String path = ResourceUtils.getURL("classpath:").getPath();
        String projectPath = path.substring(0, path.indexOf("web"));
        CeviriVO  ceviriVO = iCeviriService.getCevirByMetinId(id);
        String filePath = fileService.getPathById(ceviriVO.getCevirFileId());
        StringBuffer stringBuffer = new StringBuffer();
        FileUtils.readLines(new File(projectPath+filePath),"utf-8").forEach(stringBuffer::append);

        return new Result(true,ResultCode.SUCCESS.getCode(),stringBuffer.toString());

    }
}

