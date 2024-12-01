package com.kai.cloudshop.dto;

import com.kai.cloudshop.entity.Setmeal;
import com.kai.cloudshop.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
