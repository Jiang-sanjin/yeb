package com.example.server.service;

import com.example.server.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.RespBean;
import com.example.server.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiangsanjin
 * @since 2021-02-03
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 查询所有员工（分页）
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * 获取当前最大工号
     * @return
     */
    RespBean maxWorkId();

    /**
     * 添加员工
     * @param employee
     * @return
     */
    RespBean insertEmployee(Employee employee);

    /**
     * 根据 id 查询员工
     * @param id
     * @return
     */
    List<Employee> getEmployee(Integer id);


    /**
     * 获取所有员工套账（分页）
     * @param name
     * @return
     */
    RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size,String name);
}
