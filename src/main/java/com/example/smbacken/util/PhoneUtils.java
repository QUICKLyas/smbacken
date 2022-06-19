package com.example.smbacken.util;

public class PhoneUtils {
    /**
     * 判断是否是合法的电话格式
     * @param phone
     * @return
     */
    public static boolean isValidPhone(String phone) {
        String telRegex = "1(3[0-9]|5[012356789]|7[1235678]|8[0-9])\\d{8}$";
        // "[1]"代表第1位为数字1，"[3578]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        return phone.matches(telRegex);
    }
}
