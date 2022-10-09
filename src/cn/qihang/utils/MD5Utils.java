package cn.qihang.utils;


import java.security.MessageDigest;


/**
 * @Author: qihang
 * @Date: 2022/9/25 16:26
 * @Desc: 密码加密工具
 */
public class MD5Utils {
    public static String getPWD(String strs){

        StringBuilder sb = new StringBuilder();

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bs = digest.digest(strs.getBytes());
            for (byte b : bs) {
                int x = b & 255;
                String s = Integer.toHexString(x);
                if (x > 0 && x < 16) {
                    sb.append("0");
                    sb.append(s);
                } else {
                    sb.append(s);
                }
            }
        } catch (Exception e) {
            System.out.println("加密失败！");
        }
        return sb.toString();
    }
}
