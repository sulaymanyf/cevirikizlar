package com.yeaile.file.service.impl;


import com.yeaile.common.utils.IdWorkerUtil;
import com.yeaile.file.entity.MyFile;
import com.yeaile.file.service.IFileService;
import com.yeaile.file.mapper.FileMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-28
 */
@Service
public class FileServiceImpl implements IFileService {

    @Resource
    private FileMapper fileMapper ;

    @Override
    public String addFile(String filename,String suffixName,String filePath) {
        MyFile file = new MyFile();
        file.setId(IdWorkerUtil.getIdStr());
        file.setFileName(filename);
        file.setPath(filePath);
        file.setSuffix(suffixName);
        int insert = fileMapper.insert(file);
        return file.getId();
    }

    @Override
    public String getPathById(String id) {
        MyFile myFile = fileMapper.selectById(id);
        return myFile.getPath();
    }
}
