package com.example.server.controller;


import com.example.server.pojo.RespBean;
import com.example.server.pojo.Salary;
import com.example.server.service.ISalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *     工资账套（每个部门不同账套）
 *  前端控制器
 * </p>
 *
 * @author jiangsanjin
 * @since 2021-02-03
 */
@RestController
@RequestMapping("/salary/sob")
public class SalaryController {
    @Autowired
    private ISalaryService salaryService;

    @ApiOperation(value = "获取所有工资套账")
    @GetMapping("/")
    public List<Salary> getAllSalary(){
        //sql :
        return salaryService.list();
    }

    @ApiOperation(value = "添加工资套账")
    @PostMapping("/")
    public RespBean addSalary(@RequestBody Salary salary){
        //设置创建日期
        salary.setCreateDate(LocalDateTime.now());
        if (salaryService.save(salary)){
            //sql :
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "删除工资套账")
    @DeleteMapping("/{id}")
    public RespBean deleteSalary(@PathVariable Integer id){
        if (salaryService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "更新工资套账")
    @PutMapping("/")
    public RespBean updateSalary(@RequestBody Salary salary){
        if (salaryService.updateById(salary)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }


}
