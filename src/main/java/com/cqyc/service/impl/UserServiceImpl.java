package com.cqyc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqyc.domain.test.User;
import com.cqyc.mapper.UserMapper;
import com.cqyc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: cqyc
 * Description:
 * Created by cqyc on 19-10-5
 */
@Service
public class UserServiceImpl implements UserService  {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
///        User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
        return userMapper.findByUsername(username);
    }
}
