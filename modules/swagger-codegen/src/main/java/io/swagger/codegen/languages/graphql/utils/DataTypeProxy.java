package io.swagger.codegen.languages.graphql.utils;

public class DataTypeProxy implements DataTypeDescriptor {

    private final String javaType;
    private String graphQLDataType;
    private boolean graphQLArray;
    private String graphQLPreparedDataTypeFragment;
    private boolean isListContainer;

    public DataTypeProxy(String javaType) {
        this.javaType = javaType;
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
        return isBoolean() || isDouble() || isFloat() || isInteger() || isLong() || isString();
    }

    @Override
    public boolean isLong() {
        return "Long".equals(javaType);
    }

    @Override
    public boolean isInteger() {
        return "Integer".equals(javaType);
    }

    @Override
    public boolean isFloat() {
        return "Float".equals(javaType);
    }

    @Override
    public boolean isDouble() {
        return "Double".equals(javaType);
    }

    @Override
    public boolean isNumber() {
        return isDouble() || isLong() || isFloat() || isInteger();
    }

    @Override
    public boolean isNumeric() {
        return isNumber();
    }

    @Override
    public boolean isString() {
        return "String".equals(javaType);
    }

    @Override
    public boolean isBoolean() {
        return "Boolean".equals(javaType);
    }

    @Override
    public boolean isListContainer() {
        return isListContainer;
    }

    @Override
    public String baseType() {
        return javaType == null ? "Void" : javaType;
    }

    @Override
    public boolean baseTypeIsPrimitive() {
        return isPrimitiveType();
    }

    @Override
    public String dataType() {
        return baseType();
    }

    @Override
    public boolean isRequired() {
        return false;
    }

    public void setIsListContainer(boolean list) {
        this.isListContainer = list;
    }
}
