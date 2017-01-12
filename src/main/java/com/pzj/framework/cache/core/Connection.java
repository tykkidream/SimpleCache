package com.pzj.framework.cache.core;

/**
 * Created by Administrator on 2017-1-9.
 */
public interface Connection {
    <T> T execute(Statement statement);
}
