package com.kai.cloudshop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kai.cloudshop.dto.OrderDto;
import com.kai.cloudshop.entity.Orders;

public interface OrderService extends IService<Orders> {

    /**
     * 用户下单
     * @param orders
     */
    public void submit(Orders orders);

    /**
     * 用户个人中心订单信息查看
     * @param page
     * @param pageSize
     * @return
     */
    public Page<OrderDto> userPage(int page, int pageSize);

    /**
     * 再来一单
     *
     * @param order
     */
    public void again(Orders order);
}
