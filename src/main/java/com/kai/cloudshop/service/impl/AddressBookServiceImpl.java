package com.kai.cloudshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kai.cloudshop.entity.AddressBook;
import com.kai.cloudshop.mapper.AddressBookMapper;
import com.kai.cloudshop.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}
