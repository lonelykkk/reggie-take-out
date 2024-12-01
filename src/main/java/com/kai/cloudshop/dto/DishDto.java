package com.kai.cloudshop.dto;

import com.kai.cloudshop.entity.Dish;
import com.kai.cloudshop.entity.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
