package com.kai.cloudshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kai.cloudshop.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

}