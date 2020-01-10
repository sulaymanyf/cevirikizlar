package com.yeaile.web.config;

import com.yeaile.user.entity.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @param
 * @author sulaymanyf
 * @Description
 * @date 2020/1/10
 * @return
 **/
@Data
@EqualsAndHashCode
public class initPermission {
    private String pUrl;

    private Permission permission;


}
