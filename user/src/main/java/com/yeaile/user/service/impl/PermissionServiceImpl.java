package com.yeaile.user.service.impl;


import com.yeaile.common.domain.user.vo.PermissionVO;
import com.yeaile.user.entity.Permission;
import com.yeaile.user.mapper.PermissionMapper;
import com.yeaile.user.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-24
 */
@Service
public class PermissionServiceImpl implements IPermissionService {


    @Resource
    private PermissionMapper permissionMapper;

    @Override
    @Cacheable
    public List<PermissionVO> getAllPermissionWithRole() {
        permissionMapper.getAllPermissionWithRole();
        return null;
    }

    @Override
    public List<String> getAllPathsByUserName(String username) {
        List<String> permissionVOS = permissionMapper.getAllPathsByUserName(username);

        return permissionVOS;
    }
}
