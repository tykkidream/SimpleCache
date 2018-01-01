package com.tykkidream.cache.core;

/**
 * Created by Administrator on 2017-1-9.
 */
public interface Connection {
    <T> T execute(Statement statement);
}
