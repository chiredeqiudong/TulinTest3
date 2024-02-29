package com.it.util;

import java.util.Objects;

/**
 * @author zy293
 * 数据校验
 */
public class Regex {

    /**
     * @param email
     * 邮箱校验
     */
    public static boolean emailCheck(String email){
        if (Objects.isNull(email) || email.isEmpty()){
            return false;
        }
        String regex = "[1-9][0-9]{4,10}@qq\\.com";
        return email.matches(regex);
    }

    /**
     * 分数判别0-100保留1位小数
     */
    public static boolean scoreCheck(double grade){
        if (grade < 0){
            return false;
        }
        String score = "" + grade;
        String regex = "(([1-9]?\\d?(\\.\\d{1,2})?)|100|100\\.(0){1,2})";
        return score.matches(regex);

    }

    /**
     * 分数判别0-100保留2位小数
     */
    public static boolean phoneCheck(String phone){
        if (Objects.isNull(phone) || phone.isEmpty()){
            return false;
        }
        String regex ="1[3-9]\\d{9}";
        return phone.matches(regex);

    }
}
