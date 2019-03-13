package com.gen.generator;

import com.gen.Config;
import com.gen.models.ClassModel;
import com.gen.utils.FileUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @Author: JamalJo
 * @GitHub: https://github.com/JamalJo
 * @Desc: 负责将ClassModel对象转化为java类。是基于FreeMarker强大的模板引擎功能
 */

public class JavaGenerator implements IGenerator {
    private Template template;

    public JavaGenerator() throws IOException {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(this.getClass(), "/");
        template = configuration.getTemplate("java.ftl");

    }

    @Override
    public void generate(ClassModel classModel) throws Exception {
        String targetPath = Config.OUTPUT + Config.PKG_NAME.replace(".", "/") + "/"
                + classModel.className + ".java";
        FileUtils.mkdirs(targetPath);
        Writer writer = new OutputStreamWriter(new FileOutputStream(targetPath), "UTF-8");
        template.process(classModel, writer);
        System.out.println(" ######生成了： " + targetPath);
    }
}
