package com.yeaile.user.service.impl;

import com.yeaile.user.mapper.MenuPermissionMapper;
import com.yeaile.user.service.IMenuPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sulaymanyf
 * @since 2020-01-18
 */
@Service
public class MenuPermissionServiceImpl implements IMenuPermissionService {

    @Autowired
    private MenuPermissionMapper menuPermissionMapper ;
}
