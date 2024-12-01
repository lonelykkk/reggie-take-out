package com.kai.cloudshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kai.cloudshop.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
