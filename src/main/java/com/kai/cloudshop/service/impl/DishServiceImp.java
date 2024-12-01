package com.kai.cloudshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kai.cloudshop.common.CustomException;
import com.kai.cloudshop.dto.DishDto;
import com.kai.cloudshop.entity.Dish;
import com.kai.cloudshop.entity.DishFlavor;
import com.kai.cloudshop.mapper.DishMapper;
import com.kai.cloudshop.service.DishFlavorService;
import com.kai.cloudshop.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishServiceImp extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增商品，同时保存需求数据
     * @param dishDto
     */
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        //保存商品基本信息到商品表dish
        this.save(dishDto);

        Long dishId = dishDto.getId();//商品id

        //商品需求
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());
        //保存商品需求数据到商品需求表dish_flavor
        dishFlavorService.saveBatch(flavors);
    }

    /**
     * 根据id查询商品数据，用于回显
     * @param id
     * @return
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        //查询商品基本信息，从dish表查询
        Dish dish = this.getById(id);

        //对象拷贝
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);

        //查询当前商品对应的需求信息，从dish_flavor表查询
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(flavors);
        return dishDto;
    }

    /**
     * 修改商品
     * @param dishDto
     */
    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        //跟新dish表基本信息
        this.updateById(dishDto);
        //清理当前商品对应的需求数据(dish_flavor表的delete操作)
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dishDto.getId());
        dishFlavorService.remove(queryWrapper);
        //添加当前提交过来的需求数据(dish_flavor表的insert操作)
        List<DishFlavor> flavors = dishDto.getFlavors();

        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
    }

    /**
     * 尝试删除商品
     * @param ids
     */
    @Override
    public void removeWithDish(List<Long> ids) {

        //查询商品状态，确定是否可以删除
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(Dish::getId, ids);
        queryWrapper.eq(Dish::getStatus, 1);//1表示启售状态

        //如果不能删除，抛出一个业务异常
        int count = this.count(queryWrapper);
        if (count > 0) {
            throw new CustomException("商品正在售卖中，无法删除");
        }

        //如果可以删除，先删除商品表中的数据(dish)
        this.removeByIds(ids);

        //删除商品对应的需求表单
        LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(DishFlavor::getDishId, ids);
        dishFlavorService.remove(lambdaQueryWrapper);

    }

}
