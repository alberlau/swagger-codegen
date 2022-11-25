package io.swagger.codegen.languages.graphql.utils;

import io.swagger.codegen.languages.graphql.model.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScalarsProcessor {
    public void processScalars(GraphQlStuff graphQlStuff, boolean fileScalarDefined) {
        Set<String> types = graphQlStuff.getGraphQlTypes().stream().map(GraphQlType::getName).collect(Collectors.toSet());
        Set<String> enums = graphQlStuff.getGraphQlEnums().stream().map(GraphQlEnum::getName).collect(Collectors.toSet());

        processMutationsAndQueriesReturnTypes(graphQlStuff, types, enums);

        processQueriesParams(graphQlStuff, types, enums);

        processTypes(graphQlStuff, types, enums);

        if (fileScalarDefined && !graphQlStuff.getGraphQlScalars().contains("File")) {
            graphQlStuff.getGraphQlScalars().add("File");
        }
    }

    private static void processTypes(GraphQlStuff graphQlStuff, Set<String> types, Set<String> enums) {
        Set<String> typesAndInputsScalars = graphQlStuff.getGraphQlTypes().stream()
                .flatMap(graphQlType -> graphQlType.getProps().stream())
                .map(GraphQlDeclaration::getType).filter(s -> !types.contains(s) && !enums.contains(s) && !DataTypeResolver.isGraphQlPrimitive(s))
                .collect(Collectors.toSet());

        graphQlStuff.getGraphQlScalars().addAll(typesAndInputsScalars);
    }

    private static void processQueriesParams(GraphQlStuff graphQlStuff, Set<String> types, Set<String> enums) {
        Set<String> operationScalarParams = graphQlStuff.getGraphQlQueries().stream()
                .flatMap(baseGraphQlOperation -> baseGraphQlOperation.getParams().stream())
                .map(GraphQlDeclaration::getType).filter(s -> !types.contains(s) && !enums.contains(s) && !DataTypeResolver.isGraphQlPrimitive(s))
                .collect(Collectors.toSet());
        graphQlStuff.getGraphQlScalars().addAll(operationScalarParams);
    }

    private static void processMutationsAndQueriesReturnTypes(GraphQlStuff graphQlStuff, Set<String> types, Set<String> enums) {
        Set<String> operationScalarReturnTypes = Stream.concat(graphQlStuff.getGraphQlMutations().stream(), graphQlStuff.getGraphQlQueries().stream())
                .map(BaseGraphQlOperation::getReturnType).filter(s -> !types.contains(s) && !enums.contains(s) && !DataTypeResolver.isGraphQlPrimitive(s))
                .collect(Collectors.toSet());
        graphQlStuff.getGraphQlScalars().addAll(operationScalarReturnTypes);
    }

}
