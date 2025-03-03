package com.tdlm.adapter.wrapper;

import java.util.HashMap;
import java.util.Map;

public class ResponseWrapper {
    public static Map<String, Object> success(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", data);
        return response;
    }

    public static Map<String, Object> error(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", message);
        return response;
    }

    public static Map<String, Object> warning(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "warning");
        response.put("message", message);
        return response;
    }

    public static Map<String, Object> info(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "info");
        response.put("message", message);
        return response;
    }

    public static Map<String, Object> status(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "status");
        response.put("message", message);
        return response;
    }

    public static Map<String, Object> custom(String status, Object data, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("data", data);
        response.put("message", message);
        return response;
    }
}
