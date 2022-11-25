package io.swagger.codegen.languages.graphql.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public abstract class BaseGraphQlOperation {
    private String name;
    private String description;
    private String notes;
    private boolean hasParams;
    private String returnType;
    private String graphQLPreparedReturnDataTypeFragment;

    private Collection<GraphQlDeclaration> params = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getHasParams() {
        return hasParams;
    }

    public void setHasParams(boolean hasParams) {
        this.hasParams = hasParams;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getGraphQLPreparedReturnDataTypeFragment() {
        return graphQLPreparedReturnDataTypeFragment;
    }

    public void setGraphQLPreparedReturnDataTypeFragment(String graphQLPreparedReturnDataTypeFragment) {
        this.graphQLPreparedReturnDataTypeFragment = graphQLPreparedReturnDataTypeFragment;
    }

    public Collection<GraphQlDeclaration> getParams() {
        return params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseGraphQlOperation that = (BaseGraphQlOperation) o;
        return hasParams == that.hasParams && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(notes, that.notes) && Objects.equals(returnType, that.returnType) && Objects.equals(graphQLPreparedReturnDataTypeFragment, that.graphQLPreparedReturnDataTypeFragment) && Objects.equals(params, that.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, notes, hasParams, returnType, graphQLPreparedReturnDataTypeFragment, params);
    }

    @Override
    public String toString() {
        return "BaseGraphQlOperation{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", notes='" + notes + '\'' +
                ", hasParams=" + hasParams +
                ", returnType='" + returnType + '\'' +
                ", graphQLPreparedReturnDataTypeFragment='" + graphQLPreparedReturnDataTypeFragment + '\'' +
                ", params=" + params +
                '}';
    }
}
