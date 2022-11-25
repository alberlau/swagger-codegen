package io.swagger.codegen.languages.graphql.utils;

import io.swagger.codegen.CodegenProperty;

public class PropertyProxy implements DataTypeDescriptor {
    private final CodegenProperty original;
    private String graphQLDataType;
    private boolean graphQLArray;
    private String graphQLPreparedDataTypeFragment;

    public PropertyProxy(CodegenProperty original) {
        this.original = original;
    }

    @Override
    public String getGraphQLDataType() {
        return graphQLDataType;
    }

    @Override
    public void setGraphQLDataType(String graphQLDataType) {
        this.graphQLDataType = graphQLDataType;
    }

    @Override
    public boolean isGraphQLArray() {
        return graphQLArray;
    }

    @Override
    public void setGraphQLArray(boolean graphQLArray) {
        this.graphQLArray = graphQLArray;
    }

    @Override
    public String getGraphQLPreparedDataTypeFragment() {
        return graphQLPreparedDataTypeFragment;
    }

    @Override
    public void setGraphQLPreparedDataTypeFragment(String graphQLPreparedDataTypeFragment) {
        this.graphQLPreparedDataTypeFragment = graphQLPreparedDataTypeFragment;
    }

    @Override
    public boolean isPrimitiveType() {
        return original.isPrimitiveType || isBoolean() || isDouble() || isFloat() || isLong() || isInteger() || isString();
    }

    @Override
    public boolean isLong() {
        return original.isListContainer ? original.items.isLong : original.isLong;
    }

    @Override
    public boolean isInteger() {
        return original.isListContainer ? original.items.isInteger : original.isInteger;
    }

    @Override
    public boolean isFloat() {
        return original.isListContainer ? original.items.isFloat : original.isFloat;
    }

    @Override
    public boolean isDouble() {
        return original.isListContainer ? original.items.isDouble : original.isDouble;
    }

    @Override
    public boolean isNumber() {
        return original.isListContainer ? original.items.isNumber : original.isNumber;
    }

    @Override
    public boolean isNumeric() {
        return original.isListContainer ? original.items.isNumeric : original.isNumeric;
    }

    @Override
    public boolean isString() {
        return original.isListContainer ? original.items.isString : original.isString;
    }

    @Override
    public boolean isBoolean() {
        return original.isListContainer ? original.items.isBoolean : original.isBoolean;
    }

    @Override
    public boolean isListContainer() {
        return original.isListContainer;
    }

    @Override
    public String baseType() {
        return original.items.baseType;
    }

    @Override
    public boolean baseTypeIsPrimitive() {
        return original.items.isPrimitiveType  || original.items.isBoolean || original.items.isDouble || original.items.isFloat || original.items.isLong || original.items.isInteger || original.items.isString;
    }

    @Override
    public String dataType() {
        return original.datatype;
    }

    @Override
    public boolean isRequired() {
        return original.required;
    }
}
