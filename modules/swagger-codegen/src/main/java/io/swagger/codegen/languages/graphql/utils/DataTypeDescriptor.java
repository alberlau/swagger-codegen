package io.swagger.codegen.languages.graphql.utils;

public interface DataTypeDescriptor {
    String getGraphQLDataType();

    void setGraphQLDataType(String graphQLDataType);

    boolean isGraphQLArray();

    void setGraphQLArray(boolean graphQLArray);

    String getGraphQLPreparedDataTypeFragment();

    void setGraphQLPreparedDataTypeFragment(String graphQLPreparedDataTypeFragment);

    boolean isPrimitiveType();

    boolean isLong();

    boolean isInteger();

    boolean isFloat();

    boolean isDouble();

    boolean isNumber();

    boolean isNumeric();

    boolean isString();

    boolean isBoolean();

    boolean isListContainer();

    String baseType();

    boolean baseTypeIsPrimitive();

    String dataType();

    boolean isRequired();
}
