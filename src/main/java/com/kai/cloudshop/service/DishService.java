package com.kai.cloudshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kai.cloudshop.dto.DishDto;
import com.kai.cloudshop.entity.Dish;

import java.util.List;

public interface DishService extends IService<Dish> {

    //新增商品，同时插入商品对应的需求数据,需要操作两张表,dish、dish_flavor
    public void saveWithFlavor(DishDto dishDto);


    //根据id查询商品信息和对应的需求信息
    public DishDto getByIdWithFlavor(Long id);

    //跟新菜品信息，同时跟新需求信息
    public void updateWithFlavor(DishDto dishDto);

    public void removeWithDish(List<Long> ids);
}
