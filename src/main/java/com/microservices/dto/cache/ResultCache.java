package com.microservices.dto.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class ResultCache implements Serializable {
    public final static ResultCache EMPTY;
    @Serial
    private static final long serialVersionUID = 1L;
    private static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper()
                .registerModule(new JavaTimeModule());
//        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        MAPPER.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
        EMPTY = new ResultCache();
    }

    Object data;

    public <T> T toEntity(Class<T> clazz) {
        if (data == null) {
            return null;
        }
        try {
            if (data instanceof Map) {
                return MAPPER.convertValue(data, clazz);
            }
            if (data instanceof String) {
                return MAPPER.readValue((String) data, clazz);
            }
            if (clazz.isInstance(data)) {
                return clazz.cast(data);
            }
            throw new IllegalArgumentException("Unsupported data type for conversion: " + data.getClass().getName());
        } catch (Exception e) {
            log.error("Cannot convert data to entity: {}, error: {}", data, e.getMessage());
            return null;
        }
    }

    public boolean empty() {
        return data == null;
    }
}
