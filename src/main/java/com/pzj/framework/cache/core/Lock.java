package com.pzj.framework.cache.core;

import java.util.Date;

/**
 * Created by Administrator on 2017-3-31.
 */
public interface Lock {
    String getLock();

    String getOwner();

    Date getBeginDate();

    Date getEndDate();

    Long getTimeout();
}