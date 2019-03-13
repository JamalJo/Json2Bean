package com.gen.models;

import com.gen.Config;
import com.gen.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: JamalJo
 * @GitHub: https://github.com/JamalJo
 * @Desc: 对类结构的描述model
 */

public class ClassModel {
    public String packageName = Config.PKG_NAME;
    public String className;
    public List<Field> fields;

    public ClassModel(String className) {
        this.className = StringUtils.upperFirstChar(className);
        this.fields = new ArrayList<>();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
