package io.swagger.codegen.languages.graphql.utils;

import io.swagger.codegen.languages.graphql.model.GraphQlDeclaration;
import io.swagger.codegen.languages.graphql.model.GraphQlStuff;
import io.swagger.codegen.languages.graphql.model.GraphQlType;

import java.util.*;
import java.util.stream.Collectors;

public class InputsProcessor {
    public static final String INPUT_SUFFIX = "Input";

    public void processInputs(GraphQlStuff stuff) {
        Map<String, GraphQlType> typesByNames = stuff.getGraphQlTypes().stream().collect(Collectors.toMap((GraphQlType::getName), (o -> o)));
        Set<String> typeInputNames = new HashSet<>(typesByNames.keySet());
        Set<GraphQlDeclaration> inputs = stuff.getGraphQlMutations().stream()
                .flatMap(baseGraphQlOperation -> baseGraphQlOperation.getParams().stream())
                .filter(graphQlDeclaration -> typeInputNames.contains(graphQlDeclaration.getType())).collect(Collectors.toSet());

        Set<String> inputsNames = inputs.stream().map(GraphQlDeclaration::getType).collect(Collectors.toSet());

        Set<GraphQlType> types = stuff.getGraphQlTypes().stream().filter(graphQlType -> inputsNames.contains(graphQlType.getName())).collect(Collectors.toSet());

        Collection<GraphQlType> inputTypes = new ArrayList<>();
        findInputTypes(typesByNames, types, inputTypes);
        stuff.getGraphQlInputs().addAll(inputTypes);

        addSuffixesToInputTypes(stuff);
    }

    private static void findInputTypes(Map<String, GraphQlType> typesByNames, Set<GraphQlType> types, Collection<GraphQlType> inputTypes) {
        for (GraphQlType type : types) {
            inputTypes.add(type);
            processType(typesByNames, type, inputTypes);
        }
    }

    private static void processType(Map<String, GraphQlType> typesByNames, GraphQlType type, Collection<GraphQlType> inputTypes) {
        Collection<GraphQlDeclaration> props = type.getProps();
        for (GraphQlDeclaration prop : props) {
            GraphQlType subType = typesByNames.get(prop.getType());
            if (subType == null) {
                continue;
            }
            inputTypes.add(subType);
            processType(typesByNames, subType, inputTypes);
        }
    }

    private void addSuffixesToInputTypes(GraphQlStuff stuff) {
        Set<String> typesNames = stuff.getGraphQlTypes().stream().map(GraphQlType::getName).collect(Collectors.toSet());
        stuff.getGraphQlMutations().stream().flatMap(baseGraphQlOperation -> baseGraphQlOperation.getParams().stream())
                .filter(graphQlDeclaration -> typesNames.contains(graphQlDeclaration.getType()))
                .forEach(graphQlDeclaration -> graphQlDeclaration.setType(graphQlDeclaration.getType() + INPUT_SUFFIX));

        List<GraphQlType> newInputTypes = new ArrayList<>(stuff.getGraphQlInputs());
        for (int i = 0; i < newInputTypes.size(); i++) {
            GraphQlType type = newInputTypes.get(i);
            GraphQlType newType = type.clone();
            newInputTypes.set(i, newType);
            newType.setName(newType.getName() + INPUT_SUFFIX);
            Collection<GraphQlDeclaration> props = newType.getProps();
            for (GraphQlDeclaration prop : props) {
                if (typesNames.contains(prop.getType())) {
                    prop.setType(prop.getType() + INPUT_SUFFIX);
                }
            }
        }
        stuff.getGraphQlInputs().clear();
        stuff.getGraphQlInputs().addAll(newInputTypes);
    }
}
