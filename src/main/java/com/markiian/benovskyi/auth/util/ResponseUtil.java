package com.markiian.benovskyi.auth.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.concurrent.Callable;

public final class ResponseUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseUtil.class);

    public static ResponseEntity<?> buildErrorResponse(HttpStatus status, String message) {
        String errorMessage = "\"code\":%d,\"message\":\"%s\"";
        return ResponseEntity.status(status).body(String.format(errorMessage, status.value(), message));
    }

    public static <T> ResponseEntity<?> buildResponse(Callable<T> f) {
        T result;

        try {
            result = f.call();
        } catch (Exception e) {
            LOGGER.error("Problem executing callable F for build post response method: ", e);
            return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        if (result instanceof Optional) {
            if (((Optional) result).isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                //noinspection OptionalGetWithoutIsPresent
                return ResponseEntity.ok(((Optional) result).get());
            }
        }

        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }
}
