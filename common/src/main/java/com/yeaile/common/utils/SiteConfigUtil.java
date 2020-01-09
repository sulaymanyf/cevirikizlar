package com.yeaile.common.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * @author yefan
 * @Classname siteConfig
 * @Description TODO
 * @Date 2020/1/4 9:39 下午
 * @Created by SulaymanYf
 */

public class SiteConfigUtil {

    public static String getProjectPath() throws FileNotFoundException {
        String path = ResourceUtils.getURL("classpath:").getPath();
        String projectPath = path.substring(0, path.indexOf("web"));
        return projectPath;
    }
}
