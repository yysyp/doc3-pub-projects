package com.example.news;


import org.apache.commons.io.IOUtils;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaClient;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

import java.nio.charset.Charset;


public class JsonSchemaTest {

    @Test
    public void testSchemaValidation() throws Exception {
        try {
            String jsonSchemaString = IOUtils.toString(getClass().getResourceAsStream("/schemas/testschema2.json"), Charset.forName("UTF-8"));
            JSONObject jsonSchema = new JSONObject(
                    new JSONTokener(jsonSchemaString));

//            SchemaLoader schemaLoader = SchemaLoader.builder()
//                    .schemaClient(SchemaClient.classPathAwareClient())
//                    .schemaJson(jsonSchema)
//                    .resolutionScope("classpath://schemas/")
//                    .build();
//            Schema schema = schemaLoader.load().build();

//            Schema schema = SchemaLoader.load(jsonSchema);

            Schema schema = SchemaLoader.builder()
                    .useDefaults(true)
                    .schemaJson(jsonSchema)
                    .build()
                    .load().build();

            String jsonString = IOUtils.toString(getClass().getResourceAsStream("/test.json"), Charset.forName("UTF-8"));
            JSONObject jsonObject = new JSONObject(new JSONTokener(jsonString));
            schema.validate(jsonObject);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

}
