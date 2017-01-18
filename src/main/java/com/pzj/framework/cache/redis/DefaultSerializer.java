package com.pzj.framework.cache.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pzj.framework.cache.core.Serializer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017-1-18.
 */
class DefaultSerializer<T> implements Serializer<T> {
    private Class<T> dataClass = null;

    public DefaultSerializer(Class<T> dataClass){
        this.dataClass = dataClass;
    }

    @Override
    public T descrialize(String cacheValue) {
        T object = JSON.parseObject(cacheValue, dataClass);
        return object;
    }

    public byte[] serialize(Object value) {
        String jsonString = JSON.toJSONString(value);
        return jsonString.getBytes();
    }

    @Override
    public T descrializeMap(Map<byte[], byte[]> cacheValue) {
        if (cacheValue == null || cacheValue.isEmpty()){
            return null;
        }

        JSONObject jsonObject = new JSONObject();

        Iterator<Map.Entry<byte[], byte[]>> iterator = cacheValue.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<byte[], byte[]> next = iterator.next();
            jsonObject.put(new String(next.getKey()), JSONObject.parseObject(new String(next.getValue())));
        }

        T object = jsonObject.toJavaObject(dataClass);
        return object;
    }

    @Override
    public Map<byte[], byte[]> serializeMap(T obj) {
        if (obj == null)
            return null;

        Map<byte[], byte[]> map = new HashMap<>();
        for(Class<?> clazz = obj.getClass() ; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            try {
                putProperty(map, obj, clazz);
            } catch (Exception e) {
            }
        }
        return map;
    }

    private static void putProperty(Map<byte[], byte[]> map, Object obj, Class<?> clazz) {
        // 取出bean里的所有方法
        Method[] methods = clazz.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            // 取方法名
            String methodName = method.getName();
            // 取出方法的类型
            Class<?>[] cc = method.getParameterTypes();
            if (cc.length != 1)
                continue;

            // 如果方法名没有以set开头的则退出本次for
            if (!methodName.startsWith("set") )
                continue;

            try {
                //
                String properttName = methodName.substring(3,4).toLowerCase().concat(methodName.substring(4));
                String jsonString = JSON.toJSONString(method.invoke(obj));
                map.put(properttName.getBytes(), jsonString.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
