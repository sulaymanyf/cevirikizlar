package com.yeaile.user.service.impl;


import com.yeaile.user.mapper.RoleMenuMapper;
import com.yeaile.user.service.IRoleMenuService;
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
public class RoleMenuServiceImpl implements IRoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper ;
}
