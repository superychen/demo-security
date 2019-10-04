package com.cqyc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author: cqyc
 * Description:
 * Created by cqyc on 19-10-4
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class);
    }
}
