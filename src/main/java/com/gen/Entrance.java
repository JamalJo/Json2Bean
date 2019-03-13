package com.gen;

import com.gen.generator.IGenerator;
import com.gen.generator.JavaGenerator;
import com.gen.models.ClassModel;
import com.gen.processor.IProcessor;
import com.gen.processor.JsonProcessor;
import com.gen.utils.FileUtils;

import java.util.List;

/**
 * @Author: JamalJo
 * @GitHub: https://github.com/JamalJo
 * @Desc: 入口类，负责将JsonProcessor和CodeGenerator串起来，实现json到代码的转化
 */

public class Entrance {

    public static void main(String[] args) {
//        String[] args = {"-p", "-com.retail.android.bean", "-s", "source.json"};

        // 参数初始化
        if (!Config.init(args)) {
            return;
        }

        // json源数据
        String jsonStr = FileUtils.readFile(Config.SOURCE_JSON_PATH);

        // 解析json得到ClassModel链表
        IProcessor processor = new JsonProcessor();
        List<ClassModel> classModels = processor.parseJson2ClassModel(jsonStr);

        // 将ClassModel链表遍历生成java代码
        try {
            IGenerator generator = new JavaGenerator();
            for (ClassModel model : classModels) {
                generator.generate(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
