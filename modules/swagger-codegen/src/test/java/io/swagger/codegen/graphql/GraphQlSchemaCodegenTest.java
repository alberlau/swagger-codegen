package io.swagger.codegen.graphql;

import io.swagger.codegen.ClientOptInput;
import io.swagger.codegen.ClientOpts;
import io.swagger.codegen.CodegenConfig;
import io.swagger.codegen.DefaultGenerator;
import io.swagger.codegen.languages.GraphQlSchemaCodegen;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import io.swagger.parser.util.ParseOptions;
import org.apache.commons.io.IOUtils;
import org.junit.rules.TemporaryFolder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;

import static org.testng.Assert.assertEquals;

public class GraphQlSchemaCodegenTest {

    public TemporaryFolder folder = new TemporaryFolder();
    @BeforeMethod
    public void setUp() throws Exception {
        folder.create();
    }

    @AfterMethod
    public void tearDown() {
        folder.delete();
    }

    @Test(description = "Generate graphQL from petstore.yaml")
    public void testPetStore() throws IOException {
        baseTest("src/test/resources/2_0/petstore.yaml", "/2_0/graphql/petstore-2.0-expected-result.graphqls");
    }

    @Test(description = "Generate graphQL from wordnik.yaml")
    public void testWordnik() throws IOException {
        baseTest("src/test/resources/2_0/wordnik.yaml", "/2_0/graphql/wordnik-expected-result.graphqls");
    }

    private void baseTest(String swaggerYaml, String expectationFile) throws IOException {
        final File output = folder.getRoot();
        ParseOptions parseOptions = new ParseOptions();
        Swagger swagger = new SwaggerParser().read(swaggerYaml,null, parseOptions);
        CodegenConfig codegenConfig = new GraphQlSchemaCodegen();
        codegenConfig.setOutputDir(output.getAbsolutePath());

        ClientOptInput clientOptInput = new ClientOptInput().opts(new ClientOpts()).swagger(swagger).config(codegenConfig);

        new DefaultGenerator().opts(clientOptInput).generate();

        final File schemaFile = new File(output, "/src/main/resources/schema.graphqls");
        String actualResult = IOUtils.toString(Files.newInputStream(schemaFile.toPath()), StandardCharsets.UTF_8);
        String expectedResult = IOUtils.toString(Objects.requireNonNull(GraphQlSchemaCodegenTest.class
                .getResourceAsStream(expectationFile)), StandardCharsets.UTF_8);
        assertEquals(expectedResult, actualResult);
    }
}
