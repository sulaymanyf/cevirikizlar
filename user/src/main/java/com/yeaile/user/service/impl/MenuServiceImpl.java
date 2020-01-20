package com.yeaile.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yeaile.common.domain.user.dto.MenuDTO;
import com.yeaile.common.domain.user.vo.MenuNodeVO;
import com.yeaile.common.domain.user.vo.MenuVO;
import com.yeaile.common.utils.BeanUtil;
import com.yeaile.common.utils.IdWorker;
import com.yeaile.common.utils.IdWorkerUtil;
import com.yeaile.user.entity.Menu;
import com.yeaile.user.mapper.MenuMapper;
import com.yeaile.user.service.IMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sulaymanyf
 * @since 2020-01-18
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<MenuNodeVO> getMenuTree() {
        QueryWrapper qurey = new QueryWrapper();
        qurey.orderByAsc("sort");
        List<Menu> menuList = menuMapper.selectList(qurey);
        ArrayList<MenuNodeVO> menuNodeVOS = new ArrayList<>();
        for (Menu menuNodeVO : menuList) {
            if (menuNodeVO != null) {
                menuNodeVOS.add(new MenuNodeVO(menuNodeVO.getId(), menuNodeVO.getPid(), menuNodeVO.getName(), menuNodeVO.getIcon(), menuNodeVO.getPath(),menuNodeVO.getSort(),menuNodeVO.getCode()));
            }
        }
        List<MenuNodeVO> menuTree = ListToTree(menuNodeVOS);
        return menuTree;
    }

    @Override
    public List<MenuVO> getMenuList() {
        List<Menu> menuList = menuMapper.selectList(null);
        List<MenuVO> menuVOS = BeanUtil.copyList(menuList, MenuVO.class);
        return menuVOS;
    }

    @Override
    public void AddMenu(MenuDTO menuDTO) {
        Menu menu = BeanUtil.copy(menuDTO, Menu.class);
        if (menu.getId()==null){
            menu.setId(IdWorkerUtil.getIdStr());
            menuMapper.insert(menu);
        }else {
            Menu selectById = menuMapper.selectById(menuDTO.getId());
            menu.setId(selectById.getId());
            menuMapper.updateById(menu);
        }

    }

    @Override
    public void DeleteMenu(String id) {
        menuMapper.deleteById(id);
    }


    private static List<MenuNodeVO> ListToTree(ArrayList<MenuNodeVO> list) {

        Map<String, MenuNodeVO> mapTmp = new HashMap<>();
        for (MenuNodeVO current : list) {
            mapTmp.put(current.getId(), current);
        }
        System.out.println(mapTmp);
        List<MenuNodeVO> finalList = new ArrayList<>();
        mapTmp.forEach((k, v) -> {
            if ("".equals(v.getPid()) || "0".equals(v.getPid())) {
                finalList.add(v);
            } else {
                mapTmp.get(v.getPid()).getChildren().add(v);
            }
        });
        return finalList;
    }
}
