package com.yeaile.file.service;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-28
 */
public interface IFileService {

    String addFile(String filename, String suffixName,String filePath);

    String getPathById(String id);

}
