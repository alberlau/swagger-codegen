package io.swagger.codegen.languages.graphql.utils;

public class DataTypeResolver {
    public static void determineGraphQLDataType(DataTypeDescriptor param) {
        if (param.isPrimitiveType()) {
            if (param.isLong() || param.isInteger() || "Long".equals(param.dataType()) || "Integer".equals(param.dataType())) {
                param.setGraphQLDataType("Int");
            } else if (param.isFloat() || param.isDouble() || param.isNumber() || param.isNumeric() || "Float".equals(param.dataType()) || "Double".equals(param.dataType())) {
                param.setGraphQLDataType("Float");
            } else if (param.isString() || "String".equals(param.dataType())) {
                param.setGraphQLDataType("String");
            } else if (param.isBoolean() || "Boolean".equals(param.dataType())) {
                param.setGraphQLDataType("Boolean");
            }
        } else if (param.isListContainer()) {
            param.setGraphQLDataType(param.baseType());
        } else {
            param.setGraphQLDataType(param.dataType());
        }
        if (param.isListContainer()) {
            param.setGraphQLArray(true);
        }
        param.setGraphQLPreparedDataTypeFragment((param.isGraphQLArray() ? "[" : "")
                + param.getGraphQLDataType()
                + (param.isGraphQLArray() ? "]" : "")
                + (param.isRequired() ? "!" : ""));
    }

    public static boolean isGraphQlPrimitive(String s) {
        return "Int".equals(s) || "Float".equals(s) || "Boolean".equals(s) || "String".equals(s);
    }
}
