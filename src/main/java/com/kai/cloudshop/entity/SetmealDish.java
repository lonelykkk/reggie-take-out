package com.kai.cloudshop.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 特惠与商品关系
 */
@Data
public class SetmealDish implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    //特惠id
    private Long setmealId;


    //商品id
    private Long dishId;


    //商品名称 （冗余字段）
    private String name;

    //商品原价
    private BigDecimal price;

    //份数
    private Integer copies;


    //排序
    private Integer sort;


    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;


    //是否删除
    private Integer isDeleted;
}
