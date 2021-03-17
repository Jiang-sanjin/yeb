package com.example.server.controller;


import com.example.server.pojo.Joblevel;
import com.example.server.pojo.RespBean;
import com.example.server.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiangsanjin
 * @since 2021-02-03
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {

    @Autowired
    private IJoblevelService joblevelService;

    @ApiOperation(value = "获取职称信息")
    @GetMapping("/")
    public List<Joblevel> getAllJoblevel(){
        //  SQL语句   SELECT id,name,titleLevel,createDate,enabled FROM t_joblevel
        return joblevelService.list();
    }

    @ApiOperation(value = "添加职称信息")
    @PostMapping("/")
    public RespBean addJoblevel(@RequestBody Joblevel joblevel){
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(joblevel)){
            // sql语句    INSERT INTO t_joblevel ( name, titleLevel, createDate ) VALUES ( ?, ?, ? )
            return RespBean.success("添加职称信息成功");
        }
        return RespBean.error("添加职称信息失败");
    }

    @ApiOperation(value = "更新职称信息")
    @PutMapping("/")
    public RespBean updateJoblevel(@RequestBody Joblevel joblevel){
        if (joblevelService.updateById(joblevel)){
            //sql语句    UPDATE t_joblevel SET name=?, titleLevel=? WHERE id=?
            return RespBean.success("更新职称信息成功");
        }
        return RespBean.error("更新职称信息失败");
    }

    @ApiOperation(value = "删除职称信息")
    @DeleteMapping("/{id}")
    public RespBean deleteJoblevel(@PathVariable Integer id){
        if (joblevelService.removeById(id)){
            //SQL 语句   DELETE FROM t_joblevel WHERE id=?
            return RespBean.success("删除职称信息成功");
        }
        return RespBean.error("删除职称信息失败");
    }

    @ApiOperation(value = "批量删除职称信息")
    @DeleteMapping("/")
    public RespBean deleteJoblevelByIds(Integer[] ids){
        if (joblevelService.removeByIds(Arrays.asList(ids))){
            //sql语句   DELETE FROM t_joblevel WHERE id IN ( ? , ? )
            return RespBean.success("批量删除职称信息成功");
        }
        return RespBean.error("批量删除职称信息失败");
    }






}
