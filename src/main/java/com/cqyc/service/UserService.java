package com.cqyc.service;

import com.cqyc.domain.test.User;

/**
 * @author: cqyc
 * Description:
 * Created by cqyc on 19-10-5
 */
public interface UserService {

    User findByUsername(String username);
}
