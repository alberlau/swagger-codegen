package io.swagger.codegen.languages.graphql.model;

import java.util.Collection;
import java.util.HashSet;

public class GraphQlStuff {
    private Collection<BaseGraphQlOperation> graphQlMutations = new HashSet<>();
    private Collection<BaseGraphQlOperation> graphQlQueries = new HashSet<>();
    private Collection<GraphQlType> graphQlInputs = new HashSet<>();
    private Collection<GraphQlType> graphQlTypes = new HashSet<>();
    private Collection<String> graphQlScalars = new HashSet<>();

    private Collection<GraphQlEnum> graphQlEnums = new HashSet<>();

    public Collection<BaseGraphQlOperation> getGraphQlMutations() {
        return graphQlMutations;
    }

    public Collection<BaseGraphQlOperation> getGraphQlQueries() {
        return graphQlQueries;
    }

    public Collection<GraphQlType> getGraphQlInputs() {
        return graphQlInputs;
    }

    public Collection<GraphQlType> getGraphQlTypes() {
        return graphQlTypes;
    }

    public boolean getQueriesPresent() {
        return !graphQlQueries.isEmpty();
    }

    public boolean getMutationsPresent() {
        return !graphQlMutations.isEmpty();
    }

    public Collection<String> getGraphQlScalars() {
        return graphQlScalars;
    }

    public Collection<GraphQlEnum> getGraphQlEnums() {
        return graphQlEnums;
    }

    public void setGraphQlEnums(Collection<GraphQlEnum> graphQlEnums) {
        this.graphQlEnums = graphQlEnums;
    }
}
