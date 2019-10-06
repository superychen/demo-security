package com.cqyc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqyc.domain.test.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author: cqyc
 * Description:
 * Created by cqyc on 19-10-5
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户名查找当前用户
     * @param username 用户名
     * @return user
     */
    User findByUsername(@Param("username") String username);
}
