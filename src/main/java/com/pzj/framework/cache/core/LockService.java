package com.pzj.framework.cache.core;

/**
 * Created by Administrator on 2017-3-31.
 */
public interface LockService {
    Boolean begin(Lock lock, long waitTime);

    Boolean end(Lock lock);
}
