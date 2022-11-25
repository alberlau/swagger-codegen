package io.swagger.codegen.languages.graphql.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldUtils {
    public static void copyFields(Object src, Object dest) {
        Field[] allFields = org.apache.commons.lang3.reflect.FieldUtils.getAllFields(src.getClass());
        for (Field field : allFields) {
            if (Modifier.isFinal(field.getModifiers())) {
                continue;
            }
            try {
                Object val = org.apache.commons.lang3.reflect.FieldUtils.readField(field, src);
                org.apache.commons.lang3.reflect.FieldUtils.writeField(field, dest, val);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
