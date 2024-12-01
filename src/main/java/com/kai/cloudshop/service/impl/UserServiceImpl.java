package com.kai.cloudshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kai.cloudshop.entity.User;
import com.kai.cloudshop.mapper.UserMapper;
import com.kai.cloudshop.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{
}
