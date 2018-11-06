package com.example.admin.utils;
import com.example.common.entity.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {
    private String algorithmName="md5";
    private int hashIterations=2;
    public void encryptPassword(AdminUser user){
        String newPassword=new SimpleHash(algorithmName,user.getPassword(),ByteSource.Util.bytes(user.getUsername()),hashIterations).toHex();
    user.setPassword(newPassword);
    }
}
