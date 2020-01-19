package com.yeaile.user.service;


import com.yeaile.common.domain.user.vo.PermissionAllVO;
import com.yeaile.common.domain.user.vo.PermissionNodeVO;
import com.yeaile.common.domain.user.vo.PermissionVO;
import com.yeaile.user.entity.Permission;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-24
 */
public interface IPermissionService {

    List<PermissionVO> getAllPermissionWithRole();

    List<String> getAllPathsByUserName(String username);

    List<Permission> getAllPermission();


    Permission getPermissionByUrl(String url);

    void addPermission(List<Permission> oldPermissionList);

    List<Permission> getAllPathsByUserNameAndMethod(String url);


    List<PermissionVO> mainMenu();

    List<PermissionAllVO> getAPermission();


    List<PermissionVO> getPermission(String id);

    List<PermissionNodeVO> getPermissionTree();


}
