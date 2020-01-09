package com.yeaile.user.service;


import com.yeaile.common.domain.user.vo.PermissionVO;
import com.yeaile.user.entity.Permission;

import java.util.List;

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
}
