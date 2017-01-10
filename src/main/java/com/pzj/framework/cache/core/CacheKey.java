package com.pzj.framework.cache.core;

/**
 * Created by Administrator on 2017-1-9.
 */
public class CacheKey {
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
}
