package com.pzj.framework.cache.redis.lock;

import com.pzj.framework.cache.core.Connection;
import com.pzj.framework.cache.redis.AbstractService;

/**
 * Created by Administrator on 2017-3-31.
 */
public class LockService extends AbstractService {
    public LockService(Connection connection) {
        super(connection);
    }

}
