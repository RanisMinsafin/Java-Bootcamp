package edu.school21.converter;

import java.util.HashMap;
import java.util.Map;

public class TypeConverter {
    private static Map <Class<?>, String> typeMapping = new HashMap<>();
    static {
        typeMapping.put(String.class, "varchar");
        typeMapping.put(Integer.class, "int");
        typeMapping.put(Long.class, "bigint");
        typeMapping.put(Double.class, "double");
        typeMapping.put(Boolean.class, "boolean");
    }

    public static String getSqlType(Class<?> javaClass) {
        return typeMapping.getOrDefault(javaClass, "unknown");
    }
}
