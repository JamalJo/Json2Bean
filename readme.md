# json2bean

背景
---
目前网上有很多json转java bean的开源框架，但这些框架的都非常复杂，原理难以理解。
当你想定制一些功能，比如说加上@SerilizedName注解，想在bean的构造函数中，初始化一些field，
或者想过滤某些字段的代码生成，那么你在定制现有json转bean方案时，会感觉非常麻烦，害怕出错。

**Json2Bean**是我全新写的一个json转bean的工具，它只保留了最核心的功能，生成只带field的java类。
它的特点是，轻便易理解、已定制修改。


框架介绍
---

**JsonProcessor**
最核心的类，负责将json数据转化为ClassModel对象链表，是基于Gson进行的json解析。

**CodeGenerator**
负责将ClassModel对象转化为java类。是基于FreeMarker强大的模板引擎功能。

**Entrance**
入口类，负责将JsonProcessor和CodeGenerator串起来，实现json到代码的转化。


执行入口
---
```
Entrance.main
```

生成jar
---
```
gradle jar
```

可扩展的
---
1. 实现针对OC、TypeScript等不同语言的IGenerator，生成OC、TypeScript等语言的bean类
2. 定制JavaGenerator，支持添加@SerilizedName注解、过滤掉某些字段的代码生成
3. 实现xml的 IProcessor，支持对xml的解析


