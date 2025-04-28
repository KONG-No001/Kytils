package com.kongyu.utils.bean;

/**
 * @author Kongyu
 * @version v1.0.0
 * @since 2025/4/28
 */
public class BeanException extends RuntimeException {
    public BeanException(String message) {
        super(message);
    }

    public BeanException(String message, Throwable cause) {
        super(message, cause);
    }
}
