package com.yeaile.ceviri.service.impl;


import com.google.common.base.Charsets;
import com.yeaile.common.utils.HWPFUtil;
import com.yeaile.file.entity.MyFile;
import com.yeaile.file.mapper.FileMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeaile.ceviri.entity.Ceviri;
import com.yeaile.ceviri.entity.Metin;
import com.yeaile.ceviri.mapper.CeviriMapper;
import com.yeaile.ceviri.mapper.MetinMapper;
import com.yeaile.ceviri.service.IMetinService;
import com.yeaile.common.constant.MetinSatus;
import com.yeaile.common.domain.ceviri.dto.MetinDTO;
import com.yeaile.common.domain.ceviri.dto.MetinQueryDTO;
import com.yeaile.common.domain.ceviri.vo.MetinVO;
import com.yeaile.common.utils.BeanUtil;

import com.yeaile.common.utils.IdWorkerUtil;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharSetUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * <p>
 * 原文 服务实现类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */
@Service
public class MetinServiceImpl implements IMetinService {

    @Resource
    private MetinMapper metinMapper;

    @Resource
    private CeviriMapper ceviriMapper;

    @Resource
    private FileMapper fileMapper;


    @Override
    public MetinVO metin(String id) {
        Metin metin = metinMapper.selectById(id);
        MetinVO metinVO = BeanUtil.copy(metin, MetinVO.class);
        MyFile myFile = fileMapper.selectById(metin.getContent());
        String s = myFile.getSuffix().split("\\.")[1];
        metinVO.setFileType(s);
        return metinVO;
    }

    @Override
    public void addOrUpdateMetin(MetinDTO metinDTO) {

        Metin metin = BeanUtil.copy(metinDTO, Metin.class);
        //修改
        Metin metinOld = metinMapper.selectById(metinDTO.getId());
        if (metinOld.getStatus() == MetinSatus.END.getCode() || metinOld.getStatus() == MetinSatus.TRANSLATING.getCode()) {
            // 抛异常
            System.out.println("sssss");
        }
        metin.setId(metinOld.getId());
        metinMapper.updateById(metin);

    }

    @Override
    public IPage<MetinVO> listMetin(int current, int pageSize, MetinQueryDTO metinDTO) {
        IPage<MetinVO> page = new Page<>();
        page.setCurrent(current);
        page.setSize(pageSize);
        IPage<MetinVO> voPage = metinMapper.pageSelect(page, metinDTO);
        return voPage;
    }

    @Override
    public void addMetin(MetinDTO metinDTO) throws IOException {
        Metin metin = BeanUtil.copy(metinDTO, Metin.class);
        MyFile myFile = fileMapper.selectById(metinDTO.getFileId());

        String id = IdWorkerUtil.getIdStr();
        metin.setContent(myFile.getId());
        metin.setId(id);
        metin.setStatus(MetinSatus.NEW.getCode());
        metinMapper.insert(metin);

        // 生成译文
        Ceviri ceviri = new Ceviri();
        ceviri.setId(IdWorkerUtil.getIdStr());
        ceviri.setMetinId(id);
        ceviri.setState(MetinSatus.NEW.getCode());
        ceviriMapper.insert(ceviri);
    }


    public String readFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream(
                    filePath) {{

            }}, Charsets.UTF_8);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sb.toString();
    }
}


////        String path = ResourceUtils.getURL("classpath:").getPath();
////        String projectPath = path.substring(0, path.indexOf("web"));
////        String filePath= myFile.getPath();
////        String content = HWPFUtil.wordExtractor(projectPath + filePath,myFile.getSuffix());
//
////        File file = new File(projectPath+fileUrl);
//////        List<String> lines = new ArrayList<>();
//////        String sb = readFile(projectPath + fileUrl);
////        List<String> lines = Files.readAllLines(Paths.get(projectPath+fileUrl), StandardCharsets.UTF_8);
////        StringBuilder sb = new StringBuilder();
////        for(String line : lines){
////            sb.append(line);
////        }
//
//
////        boolean exception = true;
////        StringBuilder sb = new StringBuilder();
////        //Try the default one first.
////
////        Charset charset = Charset.defaultCharset();
////        int index = 0;
////        while(exception) {
////            try {
////                lines = Files.readAllLines(Paths.get(projectPath+fileUrl),charset);
////                for (String line : lines) {
////                    sb.append(line);
////                }
////                //No exception, just returns
////                exception = false;
////            } catch (IOException e) {
////                exception = true;
////                //Try the next charset
////                if(index<Charset.availableCharsets().values().size()) {
////                    charset = (Charset) Charset.availableCharsets().values().toArray()[index];
////                }
////                index ++;
////            }
////        }
////        try (Stream<String> stream = Files.lines(Paths.get(projectPath+fileUrl))) {
////            lines=stream.collect(Collectors.toList());
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        StringBuilder sb = new StringBuilder();
////        for (String line : lines) {
////            sb.append(line);