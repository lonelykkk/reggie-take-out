package com.kai.cloudshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kai.cloudshop.common.CustomException;
import com.kai.cloudshop.entity.Category;
import com.kai.cloudshop.entity.Dish;
import com.kai.cloudshop.entity.Setmeal;
import com.kai.cloudshop.mapper.CategoryMapper;
import com.kai.cloudshop.service.CategoryService;
import com.kai.cloudshop.service.DishService;
import com.kai.cloudshop.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类,删除之前需要进行判断
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类id进行查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishLambdaQueryWrapper);
        if (count1 > 0) {
            //已经关联商品,抛出一个业务异常
            throw new CustomException("当前分类下关联了商品，不能删除");
        }

        //查询当前分类是否关联商品，如果已经关联，抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类id进行查询
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);
        if (count2 > 0) {
            //已经关联套餐,抛出一个业务异常
            throw new CustomException("当前分类下关联了特惠套餐，不能删除");
        }

        //正常删除
        super.removeById(id);
    }
}
