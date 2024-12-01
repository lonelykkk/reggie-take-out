package com.kai.cloudshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kai.cloudshop.entity.DishFlavor;
import com.kai.cloudshop.mapper.DishFlavorMapper;
import com.kai.cloudshop.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper,DishFlavor> implements DishFlavorService {
}
