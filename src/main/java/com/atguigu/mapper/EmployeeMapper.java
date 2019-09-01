package com.atguigu.mapper;

import com.atguigu.entities.Employee;

public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);
}
