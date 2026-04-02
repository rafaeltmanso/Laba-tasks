package com.solvd.library.utils;

public class OperationResult<T> {
    private final T data;
    private final boolean success;
    private final String message;

    private OperationResult(T data, boolean success, String message) {
        this.data = data;
        this.success = success;
        this.message = message;
    }

    public static <T> OperationResult<T> success(T data) {
        return new OperationResult<>(data, true, "Operation successful");
    }

    public static <T> OperationResult<T> failure(String message) {
        return new OperationResult<>(null, false, message);
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "OperationResult{success=" + success + ", message='" + message + "', data=" + data + "}";
    }
}
