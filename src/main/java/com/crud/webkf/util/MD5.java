package com.crud.webkf.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {
    /**
     * @param text 明文
     * @param key 密钥
     * @return 密文
     */
    // 带秘钥加密
    public static String md5(String text, String key) throws Exception {
        // 加密后的字符串
        String md5str = DigestUtils.md5Hex(text + key);
        System.out.println("MD5加密后的字符串为:" + md5str);
        return md5str;
    }

    // 不带秘钥加密
    public static String md5_2(String text) {
        String md5str="";
        try{
            md5str = DigestUtils.md5Hex(text);
            System.out.println("MD52加密后的字符串为:" + md5str + "\t长度：" + md5str.length());
            return md5str;
        }catch (Exception e){
            e.printStackTrace();
        }
        // 加密后的字符串
        return md5str;
    }

    /**
     * MD5验证方法
     *
     * @param text 明文
     * @param key 密钥
     * @param md5 密文
     */
    // 根据传入的密钥进行验证
    public static boolean verify(String text, String key, String md5) throws Exception {
        String md5str = md5(text, key);
        if (md5str.equalsIgnoreCase(md5)) {
            System.out.println("MD5验证通过");
            return true;
        }
        return false;
    }

    // 测试
//    public static void main(String[] args) throws Exception {
//        // String str =
//        // "181115.041650.10.88.168.148.2665.2419425653_1";181115.040908.10.88.181.118.3013.1655327821_1
//        String str = "181115.040908.10.88.181.118.3013.1655327821_1";
//        System.out.println("加密的字符串：" + str + "\t长度：" + str.length());
//        MD5.md52(str);
//    }
}
