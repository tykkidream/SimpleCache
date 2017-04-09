package com.pzj.framework.cache.core.exception;

/**
 * Created by Saber on 2017/4/9.
 */
public class CacheException extends RuntimeException {
    public CacheException() {
        super();
    }

    public CacheException(String message) {
        super(message);
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheException(Throwable cause) {
        super(cause);
    }
}
