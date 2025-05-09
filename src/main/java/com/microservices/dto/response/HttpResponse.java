package com.microservices.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HttpResponse<T> {
    private String message;
    private HttpStatus status;
    private T data;

    public int getStatusCode() {
        return status.value();
    }
}