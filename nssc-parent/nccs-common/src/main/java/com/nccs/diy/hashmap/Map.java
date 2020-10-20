package com.nccs.diy.hashmap;

/**
 * 手写hashMap 步骤一：定义Map接口
 */
public interface Map {
    //定义方法
    public void put(Object key, Object value);

    public Object get(Object key);

    public int size();

    public boolean isEmpty();

    public String toString();

    //定义内部接口
    interface Entry {
        public Object getKey();

        public Object getValue();

        public String toString();
    }
}
