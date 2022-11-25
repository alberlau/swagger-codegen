package io.swagger.codegen.languages.graphql.model;

import java.util.Objects;

public class GraphQlDeclaration {
    private String name;
    private String type;
    private String description;
    private boolean isOfTypeArray;
    private boolean required;
    public GraphQlDeclaration() {
    }

    public GraphQlDeclaration(String name, String type, String description, boolean isOfTypeArray, boolean required) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.isOfTypeArray = isOfTypeArray;
        this.required = required;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getIsOfTypeArray() {
        return isOfTypeArray;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean getRequired() {
        return required;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsOfTypeArray(boolean ofTypeArray) {
        this.isOfTypeArray = ofTypeArray;
    }

    @Override
    public GraphQlDeclaration clone() {
        return new GraphQlDeclaration(name, type, description, isOfTypeArray, required);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphQlDeclaration that = (GraphQlDeclaration) o;
        return isOfTypeArray == that.isOfTypeArray && required == that.required && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, description, isOfTypeArray, required);
    }

    @Override
    public String toString() {
        return "GraphQlDeclaration{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", isOfTypeArray=" + isOfTypeArray +
                ", required=" + required +
                '}';
    }
}
