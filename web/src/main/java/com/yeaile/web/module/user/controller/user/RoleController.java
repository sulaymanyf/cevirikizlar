package com.yeaile.web.module.user.controller.user;


import com.yeaile.user.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-24
 */

@RestController
@RequestMapping("/api/user/")
public class RoleController {

    @Autowired
    private IRoleService roleService;
}

