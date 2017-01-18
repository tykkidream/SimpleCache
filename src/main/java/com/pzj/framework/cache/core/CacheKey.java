package com.pzj.framework.cache.core;

/**
 * Created by Administrator on 2017-1-9.
 */
public class CacheKey {
    private static String KEY_RULE = "^[a-zA-Z0-9]*:[a-zA-Z0-9:]*$";

    protected final static String Separator = ":";
    private String writer;
    private String business;
    private String key;


    public CacheKey(String writer, String business){
        setWriter(writer);
        setBusiness(business);
        this.key = createKey();
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String key() {
        return key;
    }

    protected String createKey(){
        return writer + Separator + business;
    }

    public static <T extends CacheKey> String[] keysArray(T[] keys){
        if (keys == null)
            return null;

        String[] result = new String[keys.length];
        for (int i = 0; i < keys.length; i++){
            result[i] = keys[i].key();
        }
        return result;
    }

    public static void checkKey(String... keys){
        if (keys == null){
            throw new KeyException("缓存 keys 不能为空。");
        }
        for (int i = 0; i < keys.length; i++){
            checkKey(keys[i]);
        }
    }

    public static void checkKey(String key){
        if (key == null){
            throw new KeyException("缓存 key 不能为空。");
        }
        if (!key.matches(KEY_RULE)){
            throw new KeyException("缓存 key 不能符合规则，应由多个部分组成，并以英文冒号分隔，且至少要有一个冒号。");
        }
    }
}
