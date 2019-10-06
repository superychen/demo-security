package com.cqyc;

import com.cqyc.comm.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: cqyc
 * Description:
 * Created by cqyc on 19-10-5
 */
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class Md5Test {
    @Test
    public void testMd5(){
        System.out.println(Md5Hash.addSalt("123", "demo"));
    }
}
