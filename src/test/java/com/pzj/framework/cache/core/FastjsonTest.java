package com.pzj.framework.cache.core;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * Created by Administrator on 2017-1-13.
 */
public class FastjsonTest {
    @Test
    public void test(){
        String jsonString = JSON.toJSONString("123");
        System.out.println(jsonString);
    }
}
