package com.kai.cloudshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kai.cloudshop.dto.SetmealDto;
import com.kai.cloudshop.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {

    /**
     * 新增特惠
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除特惠套餐
     * @param ids
     */
    public void removeWithDish(List<Long> ids);


    /**
     * 根据id查询对应的特惠数据
     * @param id
     * @return
     */
    public SetmealDto getByIdWithFlavor(Long id);

    /**
     * 修改特惠数据
     * @param setmealDto
     */
    public void updateWithFlavor(SetmealDto setmealDto);
}
