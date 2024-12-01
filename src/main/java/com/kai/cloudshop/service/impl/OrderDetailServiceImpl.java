package com.kai.cloudshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kai.cloudshop.entity.OrderDetail;
import com.kai.cloudshop.mapper.OrderDetailMapper;
import com.kai.cloudshop.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}