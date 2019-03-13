package com.gen.utils;

/**
 * @Author: JamalJo
 * @GitHub: https://github.com/JamalJo
 * @Desc:
 */

public class StringUtils {

    public static String upperFirstChar(String s) {
        if (s == null || s.length() <= 0) {
            return "";
        }
        char[] arr = s.toCharArray();
        if (arr[0] >= 'a' && arr[0] <= 'z') {
            arr[0] += 'A' - 'a';
        }
        return new String(arr);
    }
}
