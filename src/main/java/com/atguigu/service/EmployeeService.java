package com.atguigu.service;

import com.atguigu.entities.Employee;
import com.atguigu.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Cacheable(cacheNames = {"emp"}, key = "#id")
    public Employee getEmp(Integer id){
        System.out.println("查询 " + id + " 号员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    @CachePut(value = "emp", key = "#result.id")
    public Employee updateEmp(Employee employee){
        System.out.println("updateEmp:" + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    @CacheEvict(value = "emp", key = "#id")
    public void deleteEmp(Integer id){
        System.out.println("deleteEmp: " + id);
        employeeMapper.deleteEmpById(id);
    }

    @Caching(
            cacheable = {
                    @Cacheable(value = "emp", key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp", key = "#result.id"),
                    @CachePut(value = "emp", key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName){
        Employee employee = employeeMapper.getEmpByLastName(lastName);
        return employee;
    }

}
