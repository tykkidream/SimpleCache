package com.pzj.framework.cache.core;

/**
 * Created by Administrator on 2017-3-31.
 */
public interface LockService {
    Boolean begin(Lock lock);

    Boolean beginWithWait(Lock lock);

    Boolean end(Lock lock);
}
