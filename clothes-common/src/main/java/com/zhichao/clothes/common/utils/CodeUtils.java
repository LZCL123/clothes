package com.zhichao.clothes.common.utils;

import java.util.Random;

public class CodeUtils {
    public static String getRandomCode(int length) {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < length; i++){
            int num = random.nextInt(10);
            code.append(num);
        }
        return code.toString();
    }
}
