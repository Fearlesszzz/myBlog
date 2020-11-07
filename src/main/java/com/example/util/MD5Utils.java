package com.example.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    public static String code(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] bytes = md.digest();
            int i;
            StringBuffer stringBuffer = new StringBuffer("");
            for(int offset = 0; offset < bytes.length; offset++) {
                i = bytes[offset];
                if(i < 0)
                    i += 256;
                if(i < 16)
                    stringBuffer.append("0");
                stringBuffer.append(Integer.toHexString(i));
            }
            // 32位加密
            return stringBuffer.toString();
            // 16位加密
//            return stringBuffer.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
