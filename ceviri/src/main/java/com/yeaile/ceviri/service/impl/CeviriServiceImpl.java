package com.yeaile.ceviri.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeaile.ceviri.entity.Ceviri;
import com.yeaile.ceviri.mapper.CeviriMapper;
import com.yeaile.ceviri.service.ICeviriService;
import com.yeaile.common.constant.MetinSatus;
import com.yeaile.common.domain.ceviri.dto.CeviriDTO;
import com.yeaile.common.domain.ceviri.dto.CeviriQueryDTO;
import com.yeaile.common.domain.ceviri.vo.CeviriVO;
import com.yeaile.common.utils.BeanUtil;
import com.yeaile.common.utils.IdWorkerUtil;
import com.yeaile.common.utils.SiteConfigUtil;
import com.yeaile.file.entity.MyFile;
import com.yeaile.file.mapper.FileMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * <p>
 * 原文 服务实现类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */
@Service
public class CeviriServiceImpl implements ICeviriService {

    @Resource
    private CeviriMapper ceviriMapper;

    @Resource
    private FileMapper fileMapper;

    @Override
    public CeviriVO cevir(String id) {
        Ceviri ceviri = ceviriMapper.selectById(id);
        CeviriVO ceviriVO = BeanUtil.copy(ceviri, CeviriVO.class);
        return ceviriVO;
    }

    @Override
    public IPage<CeviriVO> listCeviri(CeviriQueryDTO ceviriQueryDTO) {
        IPage<CeviriVO> page = new Page<>();
        IPage<CeviriVO> voiPage = ceviriMapper.findPage(page, ceviriQueryDTO);
        return voiPage;
    }

    @Override
    public void saveCeviri(CeviriDTO ceviriDTO) throws Exception {
        Ceviri ceviri = ceviriMapper.selectByMetinId(ceviriDTO.getMetinId());
        Ceviri newCeviri = BeanUtil.copy(ceviriDTO, Ceviri.class);
        if (ceviri!=null && ceviri.getState() == MetinSatus.END.getCode()) {
            // 抛异常
        }
        String projectPath = SiteConfigUtil.getProjectPath()+"tempfiles/";
        // 根据翻译的名字新建文件
        String htmlPath = ceviriDTO.getUserId() + "/" + ceviriDTO.getMetinId() + ".text";
        if (ceviriDTO.getId() == null || "".equalsIgnoreCase(ceviriDTO.getId())) {
            //新增

            savaHtml(ceviriDTO, projectPath, htmlPath);
            MyFile myFile = new MyFile();
            myFile.setId(IdWorkerUtil.getIdStr());
            myFile.setPath(htmlPath);
            myFile.setSuffix(".text");
            myFile.setFileName(ceviriDTO.getTitle());
            fileMapper.insert(myFile);
            newCeviri.setId(IdWorkerUtil.getIdStr());
            newCeviri.setCevirFileId(myFile.getId());
            ceviriMapper.insert(newCeviri);

        } else {
            //修改
            ceviriMapper.updateById(newCeviri);
            savaHtml(ceviriDTO, projectPath, htmlPath);
        }


    }

    @Override
    public CeviriVO getCevirByMetinId(String id) {
        Ceviri ceviri = ceviriMapper.selectByMetinId(id);
        return BeanUtil.copy(ceviri,CeviriVO.class);
    }

    private void savaHtml(CeviriDTO ceviriDTO, String projectPath, String htmlPath) throws IOException {
        Path path = Paths.get(projectPath + htmlPath);
        Path dirPath = Paths.get(projectPath+ceviriDTO.getUserId());
        boolean fileboolean = Files.exists(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
        if (fileboolean) {
            Files.delete(path);
            Files.createFile(path);
        } else {
            Files.createDirectories(dirPath);
            Files.createFile(path);
        }
        Files.write(path, ceviriDTO.getContentHtml().getBytes());
    }
}
