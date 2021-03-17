package com.example.server.controller;


import com.example.server.pojo.Position;
import com.example.server.pojo.RespBean;
import com.example.server.service.IPositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *     职位管理
 *  前端控制器
 * </p>
 *
 * @author jiangsanjin
 * @since 2021-02-03
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    private IPositionService positionService;


    @ApiOperation(value = "获取职位信息")
    @GetMapping("/")
    public List<Position> getAllPosition(){
        // sql语句   SELECT id,name,createDate,enabled FROM t_position
        // System.out.println( positionService.list());
      return  positionService.list();
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        position.setCreateDate(LocalDateTime.now());   //当前时间
        if (positionService.save(position)){
            // sql语句   INSERT INTO t_position ( name, createDate ) VALUES ( ?, ? )
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");

    }

    @ApiOperation(value = "更新职位信息")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        if (positionService.updateById(position)){
            // sql语句     UPDATE t_position SET name=? WHERE id=?
            return RespBean.success("修改成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id){
        if (positionService.removeById(id)){
            // sql语句   DELETE FROM t_position WHERE id=?
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping("/")
    public RespBean deletePositionByIds(Integer[] ids){
        if (positionService.removeByIds(Arrays.asList(ids))){
            // sql语句    DELETE FROM t_position WHERE id IN ( ? , ? , ? )
            return RespBean.success("批量删除成功");
        }
        return RespBean.error("批量删除失败");
    }

}
