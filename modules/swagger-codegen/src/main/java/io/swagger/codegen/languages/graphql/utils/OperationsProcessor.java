package io.swagger.codegen.languages.graphql.utils;

import io.swagger.codegen.CodegenOperation;
import io.swagger.codegen.CodegenParameter;
import io.swagger.codegen.languages.graphql.model.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static io.swagger.codegen.languages.graphql.utils.DataTypeResolver.determineGraphQLDataType;

public class OperationsProcessor {

    private boolean fileScalarDefined;

    public void processOperations(Map<String, Object> superResult, GraphQlStuff graphQlStuff) {
        Map apiInfo = (Map) superResult.get("apiInfo");
        List apis = (List) apiInfo.get("apis");
        Collection<BaseGraphQlOperation> mutationOperations = new ArrayList<>();
        Collection<BaseGraphQlOperation> queryOperations = new ArrayList<>();
        fileScalarDefined = false;
        for (int i = 0; i < apis.size(); i++) {
            Map api = (Map) apis.get(i);
            Map operations = (Map) api.get("operations");
            List operation = (List) operations.get("operation");
            for (int j = 0; j < operation.size(); j++) {
                CodegenOperation operationData = (CodegenOperation) operation.get(j);
                String httpMethod = operationData.httpMethod;
                boolean isMutation = "POST".equalsIgnoreCase(httpMethod) || "PUT".equalsIgnoreCase(httpMethod) || "DELETE".equalsIgnoreCase(httpMethod);
                boolean isQuery = "GET".equalsIgnoreCase(httpMethod);

                BaseGraphQlOperation baseOperation;
                if (isMutation) {
                    baseOperation = new GraphQlMutation();
                    mutationOperations.add(baseOperation);
                } else {
                    baseOperation = new GraphQlQuery();
                    queryOperations.add(baseOperation);
                }
                baseOperation.setName(operationData.operationId);
                baseOperation.setDescription(StringUtils.isEmpty(operationData.summary) ? null : operationData.summary);
                baseOperation.setNotes(StringUtils.isEmpty(operationData.notes) ? null : operationData.notes);
                baseOperation.setHasParams(operationData.allParams.size() > 0);

                boolean isList = operationData.returnType != null && operationData.returnType.startsWith("List");
                DataTypeProxy graphQLReturnType = new DataTypeProxy(isList ? operationData.returnBaseType : operationData.returnType);
                graphQLReturnType.setIsListContainer(isList);
                determineGraphQLDataType(graphQLReturnType);
                baseOperation.setReturnType(graphQLReturnType.getGraphQLDataType());
                baseOperation.setGraphQLPreparedReturnDataTypeFragment(graphQLReturnType.getGraphQLPreparedDataTypeFragment());

                List<CodegenParameter> codegenParameterList = operationData.allParams;
                for (int k = 0; k < codegenParameterList.size(); k++) {
                    CodegenParameter codegenParameter = codegenParameterList.get(k);
                    if (!fileScalarDefined && codegenParameter.isFile) {
                        fileScalarDefined = true;
                    }
                    ParameterProxy parameterProxy = new ParameterProxy(codegenParameter);
                    determineGraphQLDataType(parameterProxy);

                    GraphQlDeclaration declaration = new GraphQlDeclaration();
                    declaration.setName(codegenParameter.paramName);
                    if (codegenParameter.isEnum) {
                        processEnum(graphQlStuff, operationData, codegenParameter, declaration);
                    } else {
                        declaration.setType(parameterProxy.getGraphQLDataType());
                    }
                    declaration.setIsOfTypeArray(parameterProxy.isGraphQLArray());
                    declaration.setRequired(parameterProxy.isRequired());
                    declaration.setDescription(codegenParameter.description);
                    baseOperation.getParams().add(declaration);
                }
            }
        }

        graphQlStuff.getGraphQlMutations().addAll(mutationOperations);
        graphQlStuff.getGraphQlQueries().addAll(queryOperations);
    }

    private static void processEnum(GraphQlStuff graphQlStuff, CodegenOperation operationData, CodegenParameter codegenParameter, GraphQlDeclaration declaration) {
        String enumName = operationData.operationIdCamelCase + codegenParameter.enumName;
        declaration.setType(enumName);
        GraphQlEnum anEnum = new GraphQlEnum();
        anEnum.setName(enumName);
        anEnum.setDescription(codegenParameter.description);
        anEnum.getValues().addAll((Collection<? extends String>) codegenParameter.allowableValues.get("values"));
        graphQlStuff.getGraphQlEnums().add(anEnum);
    }

    public boolean isFileScalarDefined() {
        return fileScalarDefined;
    }
}
