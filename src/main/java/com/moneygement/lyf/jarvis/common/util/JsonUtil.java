package com.moneygement.lyf.jarvis.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String objectToJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Error converting object to json! {}", obj);
            throw new RuntimeException("Error converting object to json!", e);
        }
    }

    public static Object jsonToObject(String str) {
        if (!isJsonFormat(str)) {
            return str;
        }

        try {
            return objectMapper.readValue(str, Object.class);
        } catch (JsonProcessingException e) {
            log.error("Error converting json to object! {} to {}", str, Object.class);
            throw new RuntimeException("Error converting json to object!", e);
        }
    }

    public static <T> T convertObject(Object obj, Class<T> clazz) {
        try {
            return objectMapper.convertValue(obj, clazz);
        } catch (Exception e) {
            log.error("Error converting object to object! {} to {}", obj, clazz);
            throw new RuntimeException("Error converting object to object!", e);
        }
    }

    public static boolean isJsonFormat(String body) {
        if (body == null || body.isEmpty()) {
            return false;
        }

        try {
            objectMapper.readTree(body);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
