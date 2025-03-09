package com.tdlm.adapter.wrapper;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ResponseWrapper {
    public static Map<String, Object> success(Object data, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", data);
        response.put("code", status);
        return response;
    }

    public static Map<String, Object> error(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", message);
        response.put("code", status);
        return response;
    }

    public static Map<String, Object> warning(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "warning");
        response.put("message", message);
        response.put("code", status);
        return response;
    }

    public static Map<String, Object> info(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "info");
        response.put("message", message);
        response.put("code", status);
        return response;
    }

    public static Map<String, Object> status(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "status");
        response.put("message", message);
        response.put("code", status);
        return response;
    }

    public static Map<String, Object> custom(Object data, String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("message", message);
        response.put("code", status);
        return response;
    }
}
