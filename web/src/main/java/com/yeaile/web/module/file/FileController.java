package com.yeaile.web.module.file;
import com.yeaile.common.result.Result;
import com.yeaile.common.result.StatusCode;
import com.yeaile.file.service.IFileService;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.model.XWPFCommentsDecorator;
import org.apache.poi.xwpf.model.XWPFParagraphDecorator;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
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
import java.time.LocalDateTime;
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
        if (!"doc".equalsIgnoreCase(suffixName)|| !"docx".equalsIgnoreCase(suffixName)|| !"text".equalsIgnoreCase(suffixName)){
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
    public void fileUpload(HttpServletResponse response , @PathVariable(value = "id" ,required = false) String id) throws IOException {
        String path = ResourceUtils.getURL("classpath:").getPath();
        String projectPath = path.substring(0, path.indexOf("web"));
        String filePath = fileService.getPathById(id);
        FileCopyUtils.copy(new FileInputStream(projectPath+filePath),response.getOutputStream());

        XWPFDocument docx = new XWPFDocument(new FileInputStream(projectPath+filePath));
        XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(docx);
        String text = xwpfWordExtractor.getText();
//        FileCopyUtils.copy(new ByteArrayInputStream(text.toString().getBytes()), response.getOutputStream());

    }
}

