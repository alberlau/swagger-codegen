package io.swagger.codegen.languages.graphql.utils;

import io.swagger.codegen.CodegenModel;
import io.swagger.codegen.CodegenProperty;
import io.swagger.codegen.languages.graphql.model.GraphQlDeclaration;
import io.swagger.codegen.languages.graphql.model.GraphQlEnum;
import io.swagger.codegen.languages.graphql.model.GraphQlStuff;
import io.swagger.codegen.languages.graphql.model.GraphQlType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static io.swagger.codegen.languages.graphql.utils.DataTypeResolver.determineGraphQLDataType;

public class ModelsProcessor {
    public void processModels(Map<String, Object> superResult, GraphQlStuff graphQlStuff) {
        List models = (List) superResult.get("models");
        for (int i = 0, modelsSize = models.size(); i < modelsSize; i++) {
            Map model = (Map) models.get(i);
            CodegenModel modelData = (CodegenModel) model.get("model");
            if (modelData.vars.isEmpty()) {
                continue;
            }

            GraphQlType graphQlType = new GraphQlType();
            graphQlType.setName(modelData.classname);
            graphQlType.setDescription(modelData.description);
            graphQlStuff.getGraphQlTypes().add(graphQlType);

            ArrayList<GraphQlDeclaration> graphQlDeclarations = new ArrayList<>();
            graphQlType.setProps(graphQlDeclarations);

            List<CodegenProperty> vars = modelData.vars;
            for (int j = 0, varsSize = vars.size(); j < varsSize; j++) {
                CodegenProperty prop = vars.get(j);
                PropertyProxy propertyProxy = new PropertyProxy(prop);
                GraphQlDeclaration graphQlDeclaration = new GraphQlDeclaration();

                if (prop.isEnum) {
                    processEnum(graphQlStuff, modelData, prop, graphQlDeclaration);
                } else {
                    determineGraphQLDataType(propertyProxy);
                    graphQlDeclaration.setType(propertyProxy.getGraphQLDataType());
                }

                graphQlDeclaration.setName(prop.name);
                graphQlDeclaration.setIsOfTypeArray(propertyProxy.isGraphQLArray());
                graphQlDeclaration.setRequired(propertyProxy.isRequired());
                graphQlDeclaration.setDescription(prop.description);
                graphQlDeclarations.add(graphQlDeclaration);

            }

        }
    }

    private static void processEnum(GraphQlStuff graphQlStuff, CodegenModel modelData, CodegenProperty prop, GraphQlDeclaration graphQlDeclaration) {
        GraphQlEnum anEnum = new GraphQlEnum();
        String enumName = modelData.classname + prop.enumName;
        anEnum.setName(enumName);
        anEnum.setDescription(prop.description);
        anEnum.getValues().addAll((Collection<? extends String>) prop.allowableValues.get("values"));
        graphQlStuff.getGraphQlEnums().add(anEnum);
        graphQlDeclaration.setType(enumName);
    }
}
