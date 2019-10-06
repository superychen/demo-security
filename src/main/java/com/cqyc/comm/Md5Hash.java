package com.cqyc.comm;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author: cqyc
 * Description:
 * Created by cqyc on 19-10-5
 */
public class Md5Hash {
    public static String addSalt(String password,String username){
        String algorithmName = "MD5";
        //从前台获取密码
        Object credentials = password;
        //盐值
        Object salt = ByteSource.Util.bytes(username);
        //加密的次数
        int hashIterations = 10;
        Object result = new SimpleHash(algorithmName, credentials, salt, hashIterations);
        return result.toString();
    }
}
