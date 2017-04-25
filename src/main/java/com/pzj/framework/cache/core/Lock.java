package com.pzj.framework.cache.core;

import java.util.Date;

/**
 * Created by Administrator on 2017-3-31.
 */
public interface Lock {
    String getLock();

    void setLock(String lock);

    String getOwner();

    void setOwner(String owner);

    Date getBeginDate();

    void setBeginDate(Date beginDate);

    Date getEndDate();

    void setEndDate(Date endDate);

    Long getTimeout();

    void setTimeout(Long timeout);
}