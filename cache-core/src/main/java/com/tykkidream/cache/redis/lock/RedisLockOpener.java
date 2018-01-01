package com.tykkidream.cache.redis.lock;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Saber on 2017/4/9.
 */
public class RedisLockOpener implements Serializable {
    private String owner;

    private Date beginDate;

    private Date endDate;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


}
