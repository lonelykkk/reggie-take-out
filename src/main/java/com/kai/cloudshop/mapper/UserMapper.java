package com.kai.cloudshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kai.cloudshop.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Mapper
@Service
public interface UserMapper extends BaseMapper<User>{
}
