package com.gen.processor;

import com.gen.Config;
import com.gen.models.ClassModel;
import com.gen.models.Field;
import com.gen.utils.StringUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: JamalJo
 * @GitHub: https://github.com/JamalJo
 * @Desc: 最核心的类，负责将json数据转化为ClassModel对象链表，是基于Gson进行的json解析
 */

public class JsonProcessor implements IProcessor {

    private List<ClassModel> classModels;
    private JsonParser parser;

    public JsonProcessor() {
        classModels = new ArrayList<>();
        parser = new JsonParser();
    }

    @Override
    public List<ClassModel> parseJson2ClassModel(String sourceStr) {
        JsonElement root = parser.parse(sourceStr);
        parse(Config.ROOT_CLASS_NAME, root, null, true);
        return classModels;
    }

    private void parse(String key, JsonElement root, ClassModel rootModel, boolean addFieldToRoot) {
        if (root == null) {
            return;
        }
        if (root.isJsonObject()) {
            ClassModel classModel = new ClassModel(key);
            classModels.add(classModel);
            if (rootModel == null) {
                rootModel = classModel;
            }
            if (!addFieldToRoot) {
                rootModel.fields.add(new Field(key, root));
            }
            JsonObject object = root.getAsJsonObject();
            for (String itemKey : object.keySet()) {
                parse(itemKey, object.get(itemKey), classModel, false);
            }
        } else if (root.isJsonPrimitive()) {
            rootModel.fields.add(new Field(key, root.getAsJsonPrimitive()));
        } else if (root.isJsonArray()) {
            Field arrayField = new Field(key, root.getAsJsonArray());
            rootModel.fields.add(arrayField);
            parseArray(root.getAsJsonArray(), key, arrayField, "List<>");
        }
    }

    private void parseArray(JsonArray jsonArray, String key, Field arrayField, String generic) {
        if (!jsonArray.iterator().hasNext()) {
            return;
        }
        JsonElement element = jsonArray.iterator().next();
        if (element.isJsonObject()) {
            arrayField.setType(insertGeneric(generic, StringUtils.upperFirstChar(key) + "Item"));
            parse(key + "Item", element, null, true);
        } else if (element.isJsonPrimitive()) {
            JsonPrimitive primitive = element.getAsJsonPrimitive();
            if (primitive.isString()) {
                arrayField.setType(insertGeneric(generic, "String"));
            } else if (primitive.isBoolean()) {
                arrayField.setType(insertGeneric(generic, "Boolean"));
            } else if (primitive.isNumber()) {
                arrayField.setType(insertGeneric(generic, "Integer"));
            }
        } else if (element.isJsonArray()) {
            parseArray(element.getAsJsonArray(), key, arrayField, insertGeneric(generic, "List<>"));
        }
    }

    private String insertGeneric(String generic, String key) {
        StringBuilder genericBuilder = new StringBuilder(generic);
        int index = genericBuilder.indexOf("<>");
        genericBuilder.insert(index + 1, key);
        return genericBuilder.toString();
    }
}
