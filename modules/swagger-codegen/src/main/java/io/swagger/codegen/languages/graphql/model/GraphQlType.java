package io.swagger.codegen.languages.graphql.model;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GraphQlType {
    private String name;
    private String description;
    private Collection<GraphQlDeclaration> props;

    public GraphQlType() {
    }

    public GraphQlType(String name, String description, Collection<GraphQlDeclaration> props) {
        this.name = name;
        this.description = description;
        this.props = props;
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

    public Collection<GraphQlDeclaration> getProps() {
        return props;
    }

    public void setProps(Collection<GraphQlDeclaration> props) {
        this.props = props;
    }

    @Override
    public GraphQlType clone() {
        List<GraphQlDeclaration> newProps = props.stream().map(GraphQlDeclaration::clone).collect(Collectors.toList());
        return new GraphQlType(name, description, newProps);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphQlType that = (GraphQlType) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(props, that.props);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, props);
    }

    @Override
    public String toString() {
        return "GraphQlType{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", props=" + props +
                '}';
    }
}
