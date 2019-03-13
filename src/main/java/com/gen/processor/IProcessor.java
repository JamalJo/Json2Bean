package com.gen.processor;

import com.gen.models.ClassModel;

import java.util.List;

/**
 * @Author: JamalJo
 * @GitHub: https://github.com/JamalJo
 * @Desc:
 */
public interface IProcessor {
    List<ClassModel> parseJson2ClassModel(String sourceStr);
}
