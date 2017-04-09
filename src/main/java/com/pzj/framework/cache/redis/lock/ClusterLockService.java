package com.pzj.framework.cache.redis.lock;

import com.pzj.framework.cache.core.Connection;

/**
 * Created by Saber on 2017/4/9.
 */
public class ClusterLockService extends SimpleLockService {
    public ClusterLockService(Connection connection) {
        super(connection);
    }
}
