package com.gen.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author: JamalJo
 * @GitHub: https://github.com/JamalJo
 * @Desc:
 */

public class FileUtils {

    //创建所有目录
    public static void mkdirs(String dir) {
        File f = new File(dir);
        if (!f.getParentFile().exists()) { //判断父目录路径是否存在，即test.txt前的I:\a\b\
            f.getParentFile().mkdirs(); //不存在则创建父目录
        }
    }

    public static String readFile(String path) {
        FileInputStream inputStream = null;
        String ret = "";
        try {
            inputStream = new FileInputStream(path);
            byte[] buffer = new byte[1000];
            int len = inputStream.read(buffer);
            while (len > 0) {
                ret += new String(buffer, 0, len);
                len = inputStream.read(buffer);
            }
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }
}
