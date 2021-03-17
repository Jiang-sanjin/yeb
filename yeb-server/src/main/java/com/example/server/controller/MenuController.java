package com.example.server.controller;


import com.example.server.pojo.Menu;
import com.example.server.service.IAdminService;
import com.example.server.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  菜单 前端控制器
 *
 *  菜单放在系统管理    数据库设计好路径  需要需改"/menu" 为 “/system/cfg”
 * </p>
 *
 * @author jiangsanjin
 * @since 2021-01-30
 */
@RestController
@RequestMapping("/system/cfg")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "通过用户id查询菜单列表")
    @GetMapping("menu")
    public List<Menu> getMenusByAdminId(){

        return menuService.getMenusByAdminId();

    }

}
