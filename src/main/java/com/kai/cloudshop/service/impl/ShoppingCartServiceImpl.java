package com.kai.cloudshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kai.cloudshop.entity.ShoppingCart;
import com.kai.cloudshop.mapper.ShoppingCartMapper;
import com.kai.cloudshop.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}
