package com.kai.cloudshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kai.cloudshop.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
