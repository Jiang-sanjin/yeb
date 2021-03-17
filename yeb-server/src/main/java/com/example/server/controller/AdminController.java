package com.example.server.controller;


import com.example.server.pojo.Admin;
import com.example.server.pojo.RespBean;
import com.example.server.pojo.Role;
import com.example.server.service.IAdminService;
import com.example.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *     操作员
 *  前端控制器
 * </p>
 *
 * @author jiangsanjin
 * @since 2021-02-03
 */
@RestController
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/")
    public List<Admin> getAllAdmins(String keywords){
        return adminService.getAllAdmins(keywords);
    }

    @ApiOperation(value = "更新操作员")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin){
        if ( adminService.updateById(admin)){
            //sql语句  UPDATE t_admin SET name=?, phone=?, telephone=?, address=?, enabled=?, username=?, userFace=? WHERE id=?
            return RespBean.success("更新操作员成功");
        }
       return RespBean.error("更新操作员失败");
    }

    @ApiOperation(value = "删除操作员")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id){
        if ( adminService.removeById(id)){
            //sql语句  DELETE FROM t_admin WHERE id=?
           return RespBean.success("删除操作员成功");
        }
       return RespBean.error("删除操作员失败");
    }

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.list();
    }

    @ApiOperation(value = "更新操作员的角色")
    @PutMapping("/role")
    public RespBean updateAdminRole(Integer adminId,Integer[] rids){
        return adminService.updateAdminRole(adminId,rids);
    }

}
