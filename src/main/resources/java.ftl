package ${packageName};

public class ${className} {

    <#list fields as field>
    public ${field.type} ${field.key};
    </#list>

    public ${className}() {
    }
}