package com.gen.models;

import com.gen.utils.StringUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

/**
 * @Author: JamalJo
 * @GitHub: https://github.com/JamalJo
 * @Desc: 对类中Field结构的描述model
 */

public class Field {
    public String key;
    public String type;

    public Field(String key, JsonElement element) {
        this.key = key;

        if (element.isJsonPrimitive()) {
            JsonPrimitive primitive = element.getAsJsonPrimitive();
            if (primitive.isBoolean()) {
                type = "boolean";
            } else if (primitive.isNumber()) {
                type = "int";
            } else if (primitive.isString()) {
                type = "String";
            }
        } else if (element.isJsonObject()) {
            type = StringUtils.upperFirstChar(key);
        } else if (element.isJsonArray()) {
            type = "List";
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
