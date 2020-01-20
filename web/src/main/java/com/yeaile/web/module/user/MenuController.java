package com.yeaile.web.module.user;

import com.yeaile.common.domain.user.dto.MenuDTO;
import com.yeaile.common.domain.user.vo.MenuNodeVO;
import com.yeaile.common.domain.user.vo.MenuVO;
import com.yeaile.common.domain.user.vo.PermissionVO;
import com.yeaile.common.result.Result;
import com.yeaile.common.result.ResultCode;
import com.yeaile.common.result.StatusCode;
import com.yeaile.user.service.IMenuService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sulaymanyf
 * @since 2020-01-18
 */

@RestController
@RequestMapping(name = "菜单管理",value = "/api/cevir-kizar/menu")
public class MenuController {

    @Resource
    private IMenuService menuService;


    @RequestMapping(name = "获取菜单树形结构",value = "v1/menu",method = RequestMethod.GET)
    public Result getMenuTree(){
        List<MenuNodeVO> menuNodeVOList =  menuService.getMenuTree();
        return new Result(true, StatusCode.OK,menuNodeVOList);
    }

    @RequestMapping(name = "获取菜单列表",value = "v1/menuList",method = RequestMethod.GET)
    public Result getMenuList(){
        List<MenuVO> menuNodeVOList =  menuService.getMenuList();
        return new Result(true, StatusCode.OK,menuNodeVOList);
    }

    @RequestMapping(name = "添加或修改菜单",value = "v1/menu",method = RequestMethod.PUT)
    public Result AddOrUpdateMenu(@RequestBody MenuDTO menuDTO){
         menuService.AddMenu(menuDTO);
        List<MenuNodeVO> menuNodeVOList =  menuService.getMenuTree();
        return new Result(true, StatusCode.OK, ResultCode.SUCCESS.getMessage(),menuNodeVOList);
    }

    @RequestMapping(name = "删除菜单",value = "v1/menu/{id}",method = RequestMethod.DELETE)
    public Result DeleteMenu(@PathVariable String id){
        menuService.DeleteMenu(id);
        List<MenuNodeVO> menuNodeVOList =  menuService.getMenuTree();
        return new Result(true, StatusCode.OK, ResultCode.SUCCESS.getMessage(),menuNodeVOList);
    }

}

