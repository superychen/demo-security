package com.cqyc.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: cqyc
 * Description:
 * Created by cqyc on 19-10-4
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        String encode = new BCryptPasswordEncoder().encode(charSequence);
        return encode;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        boolean matches = new BCryptPasswordEncoder().matches(charSequence, s);
        return matches;
    }
}
