package com.gen;

/**
 * @Author: JamalJo
 * @GitHub: https://github.com/JamalJo
 * @Desc:
 */

public class Config {
    public final static String OUTPUT = "output/";
    public final static String ROOT_CLASS_NAME = "Root";
    public static String PKG_NAME = "com.jamaljo.bean";
    public static String SOURCE_JSON_PATH = "source.json";

    public static String ERROR_INPUT_MSG = "参数输入错误,请按照 \"-s 源json数据 -p 包名\" 格式输入, 如: \n"
            + "java -jar json2bean.jar -s source.json -p com.retail.android.bean";

    public static boolean init(String[] args) {
        if (args == null) {
            return false;
        }
        boolean isPackageName = false;
        boolean isSourceJson = false;
        for (String arg : args) {
            if (arg.equals("-p")) {
                if (isPackageName || isSourceJson) {
                    System.out.println(ERROR_INPUT_MSG);
                    return false;
                }
                isPackageName = true;
            } else if (arg.equals("-s")) {
                if (isPackageName || isSourceJson) {
                    System.out.println(ERROR_INPUT_MSG);
                    return false;
                }
                isSourceJson = true;
            } else if (!arg.contains("-")) {
                if (isPackageName) {
                    PKG_NAME = arg;
                    isPackageName = false;
                } else if (isSourceJson) {
                    SOURCE_JSON_PATH = arg;
                    isSourceJson = false;
                } else {
                    System.out.println(ERROR_INPUT_MSG);
                    return false;
                }
            } else {
                System.out.println(ERROR_INPUT_MSG);
                return false;
            }
        }
        return true;
    }
}
