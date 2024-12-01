package com.kai.cloudshop.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kai.cloudshop.common.BaseContext;
import com.kai.cloudshop.common.R;
import com.kai.cloudshop.dto.OrderDto;
import com.kai.cloudshop.entity.OrderDetail;
import com.kai.cloudshop.entity.Orders;
import com.kai.cloudshop.entity.ShoppingCart;
import com.kai.cloudshop.service.OrderDetailService;
import com.kai.cloudshop.service.OrderService;
import com.kai.cloudshop.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 订单
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ShoppingCartService shoppingCartService;
    /**
     * 用户下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        log.info("订单数据：{}",orders);
        orderService.submit(orders);
        return R.success("下单成功");
    }

    /**
     * 订单明细页面显示
     * @param page
     * @param pageSize
     * @return
     */
/*    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {

        //页面构造器
        Page<Orders> pageInfo = new Page<>(page, pageSize);

        //查询所有orders表信息
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Orders::getOrderTime);
        orderService.page(pageInfo, queryWrapper);

        return R.success(pageInfo);
    }*/
    /**
     * 页面显示——输入框查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, Long number, String beginTime, String endTime){
        //log.info("beginTime:{}",beginTime);
        //log.info("endTime:{}",endTime);
        //页面构造器
        Page<Orders> pageInfo = new Page<>(page, pageSize);

        //查询所有orders表信息
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        //查询name
        if (number != null){
            queryWrapper.like(Orders::getNumber, number);
        }
        //查询beginTime 大于等于这个时间
        if (beginTime != null){
            queryWrapper.ge(Orders::getOrderTime, beginTime);
        }
        //查询endTime 小于等于这个时间
        if (endTime != null){
            queryWrapper.le(Orders::getOrderTime, endTime);
        }
        queryWrapper.orderByDesc(Orders::getOrderTime);
        orderService.page(pageInfo, queryWrapper);

        return R.success(pageInfo);
    }

    /**
     * 订单状态修改——管理端
     * @param orders
     * @return
     */
    @PutMapping
    public R<String> order(@RequestBody Orders orders){
        //log.info("orders:{}", orders);
        Orders order = orderService.getById(orders.getId());
        if (order.getStatus() == 2){
            orders.setStatus(3);
            orderService.updateById(orders);
            return R.success("订单派送成功");
        }else {
            orders.setStatus(4);
            orderService.updateById(orders);
            return R.success("订单已完成");
        }
    }

    /**
     * 用户个人中心订单信息查看
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/userPage")
    public R<Page> userPage(int page, int pageSize) {
        Page<OrderDto> dtoPage = orderService.userPage(page, pageSize);
        return R.success(dtoPage);
    }

    /**
     * 再来一单
     * @param order
     * @return
     */
    /*@PostMapping("/again")
    public R<String> again(@RequestBody Orders order) {
        orderService.again(order);
        return R.success("再来一单啊");
    }
*/

    /**
     * 再来一单
     * @param map
     * @return
     */
    @PostMapping("/again")
    public R<String> againSubmit(@RequestBody Map<String,String> map){
        String ids = map.get("id");

        long id = Long.parseLong(ids);

        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getOrderId,id);
        //获取该订单对应的所有的订单明细表
        List<OrderDetail> orderDetailList = orderDetailService.list(queryWrapper);

        //通过用户id把原来的购物车给清空，这里的clean方法就是之前的购物车清空方法，我给写到service中去了，这样可以通过接口复用代码
        //shoppingCartService.clean();

        //获取用户id
        Long userId = BaseContext.getCurrentId();
        List<ShoppingCart> shoppingCartList = orderDetailList.stream().map((item) -> {
            //把从order表中和order_details表中获取到的数据赋值给这个购物车对象
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUserId(userId);
            shoppingCart.setImage(item.getImage());
            Long dishId = item.getDishId();
            Long setmealId = item.getSetmealId();
            if (dishId != null) {
                //如果是菜品那就添加菜品的查询条件
                shoppingCart.setDishId(dishId);
            } else {
                //添加到购物车的是套餐
                shoppingCart.setSetmealId(setmealId);
            }
            shoppingCart.setName(item.getName());
            shoppingCart.setDishFlavor(item.getDishFlavor());
            shoppingCart.setNumber(item.getNumber());
            shoppingCart.setAmount(item.getAmount());
            shoppingCart.setCreateTime(LocalDateTime.now());
            return shoppingCart;
        }).collect(Collectors.toList());

        //把携带数据的购物车批量插入购物车表  这个批量保存的方法要使用熟练！！！
        shoppingCartService.saveBatch(shoppingCartList);

        return R.success("操作成功");
    }




}