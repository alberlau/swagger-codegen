package io.swagger.codegen.languages.graphql.utils;

import io.swagger.codegen.CodegenParameter;

public class ParameterProxy implements DataTypeDescriptor {
    private String graphQLDataType;
    private boolean graphQLArray;
    private String graphQLPreparedDataTypeFragment;
    private final CodegenParameter original;

    public ParameterProxy(CodegenParameter codegenParameter) {
        original = codegenParameter;
    }

    public String getGraphQLDataType() {
        return graphQLDataType;
    }

    public void setGraphQLDataType(String graphQLDataType) {
        this.graphQLDataType = graphQLDataType;
    }

    public boolean isGraphQLArray() {
        return graphQLArray;
    }

    public void setGraphQLArray(boolean graphQLArray) {
        this.graphQLArray = graphQLArray;
    }

    public String getGraphQLPreparedDataTypeFragment() {
        return graphQLPreparedDataTypeFragment;
    }

    public void setGraphQLPreparedDataTypeFragment(String graphQLPreparedDataTypeFragment) {
        this.graphQLPreparedDataTypeFragment = graphQLPreparedDataTypeFragment;
    }

    @Override
    public boolean isPrimitiveType() {
        return original.isPrimitiveType;
    }

    @Override
    public boolean isLong() {
        return original.isLong;
    }

    @Override
    public boolean isInteger() {
        return original.isInteger;
    }

    @Override
    public boolean isFloat() {
        return original.isFloat;
    }

    @Override
    public boolean isDouble() {
        return original.isDouble;
    }

    @Override
    public boolean isNumber() {
        return original.isNumber;
    }

    @Override
    public boolean isNumeric() {
        return original.isNumeric;
    }

    @Override
    public boolean isString() {
        return original.isString;
    }

    @Override
    public boolean isBoolean() {
        return original.isBoolean;
    }

    @Override
    public boolean isListContainer() {
        return original.isListContainer;
    }

    @Override
    public String baseType() {
        return original.baseType;
    }

    @Override
    public boolean baseTypeIsPrimitive() {
        if (original.isListContainer) {
            return original.items.isPrimitiveType;
        }
        return original.isPrimitiveType;
    }

    @Override
    public String dataType() {
        return original.dataType;
    }

    @Override
    public boolean isRequired() {
        return original.required;
    }

}
