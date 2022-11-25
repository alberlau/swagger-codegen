package io.swagger.codegen.languages.graphql.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class GraphQlEnum {
    private String name;
    private String description;
    private Set<String> values = new HashSet<>();

    public GraphQlEnum() {
    }

    public GraphQlEnum(String name, String description, Set<String> values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getValues() {
        return values;
    }

    public void setValues(Set<String> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphQlEnum anEnum = (GraphQlEnum) o;
        return Objects.equals(name, anEnum.name) && Objects.equals(description, anEnum.description) && Objects.equals(values, anEnum.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, values);
    }

    @Override
    public String toString() {
        return "GraphQlEnum{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", values=" + values +
                '}';
    }
}
