package io.swagger.codegen.languages;

import io.swagger.codegen.CodegenConfig;
import io.swagger.codegen.CodegenType;
import io.swagger.codegen.DefaultCodegen;
import io.swagger.codegen.SupportingFile;
import io.swagger.codegen.languages.graphql.model.GraphQlStuff;
import io.swagger.codegen.languages.graphql.utils.InputsProcessor;
import io.swagger.codegen.languages.graphql.utils.ModelsProcessor;
import io.swagger.codegen.languages.graphql.utils.OperationsProcessor;
import io.swagger.codegen.languages.graphql.utils.ScalarsProcessor;

import java.io.File;
import java.util.Map;

public class GraphQlSchemaCodegen extends DefaultCodegen implements CodegenConfig {

    protected String schemaFileName = "schema.graphqls";
    protected InputsProcessor inputsProcessor;
    protected ModelsProcessor modelsProcessor;
    protected OperationsProcessor operationsProcessor;
    protected ScalarsProcessor scalarsProcessor;

    public GraphQlSchemaCodegen() {
        super();
        inputsProcessor = new InputsProcessor();
        modelsProcessor = new ModelsProcessor();
        operationsProcessor = new OperationsProcessor();
        scalarsProcessor = new ScalarsProcessor();
        init();
    }

    public GraphQlSchemaCodegen(InputsProcessor inputsProcessor, ModelsProcessor modelsProcessor, OperationsProcessor operationsProcessor, ScalarsProcessor scalarsProcessor) {
        super();
        this.inputsProcessor = inputsProcessor;
        this.modelsProcessor = modelsProcessor;
        this.operationsProcessor = operationsProcessor;
        this.scalarsProcessor = scalarsProcessor;
        init();
    }

    private void init() {
        modelTemplateFiles.clear();
        apiTemplateFiles.clear();
        apiTestTemplateFiles.clear();
        modelDocTemplateFiles.clear();
        apiDocTemplateFiles.clear();

        supportingFiles.add(new SupportingFile("graphql-schema.mustache",
                "src.main.resources".replace(".", File.separator), schemaFileName));
        this.embeddedTemplateDir = this.templateDir = "graphql";

    }

    @Override
    public Map<String, Object> postProcessSupportingFileData(Map<String, Object> objs) {

        Map<String, Object> superResult = super.postProcessSupportingFileData(objs);
        GraphQlStuff graphQlStuff = new GraphQlStuff();

        operationsProcessor.processOperations(superResult, graphQlStuff);

        modelsProcessor.processModels(superResult, graphQlStuff);

        inputsProcessor.processInputs(graphQlStuff);

        scalarsProcessor.processScalars(graphQlStuff, operationsProcessor.isFileScalarDefined());

        superResult.put("graphql-stuff", graphQlStuff);
        return superResult;
    }

    @Override
    public CodegenType getTag() {
        return CodegenType.SERVER;
    }

    @Override
    public String getName() {
        return "graphql";
    }

    @Override
    public String getHelp() {
        return "Generates GraphQL Schema";
    }

    public String getSchemaFileName() {
        return schemaFileName;
    }

    public void setSchemaFileName(String schemaFileName) {
        this.schemaFileName = schemaFileName;
    }
}
