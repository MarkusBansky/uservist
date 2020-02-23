package com.markiian.benovskyi.auth.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ResponseUtil {
    private static String errorMessage = "\"code\":%d,\"message\":\"%s\"";

    public static ResponseEntity<?> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(String.format(errorMessage, status.value(), message));
    }
}
