package com.kai.cloudshop.dto;

import com.kai.cloudshop.entity.OrderDetail;
import com.kai.cloudshop.entity.Orders;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto extends Orders {
    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;
}
