package com.nccs.diy.hashmap;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-20 14:40
 * @description: 手写hashMap步骤二：定义HashMap类 实现Map接口
 **/

public class HashMap implements Map {

    public Entry[] table;  //指向哈希表的数组引用

    int size;//键值对的数量

    public HashMap() {
        table = new Entry[16];//哈希表主数组默认长度16
    }


    @Override
    public void put(Object key, Object value) {

        int hash = key.hashCode();//计算key的哈希码
        int index = hash % table.length;//根据key的哈希码计算存储位置
        if (table[index] == null) { //判断数组中是否已存在元素
            //如果不存在值，则新建一个entry对象存入table数组中
            table[index] = new Entry(hash, key, value);
            size++; //table中键值对数量+1
        } else {
            //如果table[index]中已经存在元素，则取出该元素（该元素为链表结构）
            Entry entry = table[index];
            //Entry previousEntry = null;

            //遍历该链表结构的元素，判断key值是否相同
            while (entry != null) {
                //如果找到了相同的key
                if (entry.getKey().equals(key)) {
                    //新的value替代旧的value
                    entry.value = value;
                    return;
                }
                //previousEntry = entry;
                entry = entry.next; //获取链表中的下一个entry元素
            }
            //遍历完链表结构的元素，如果没有相同的key，则在链表最后添加一个节点
            Entry firstEntry = table[index];
            table[index] = new Entry(hash, key, value, firstEntry);
            size++;
        }
    }

    @Override
    public Object get(Object key) {
        //计算哈希码
        int hash = key.hashCode();
        //根据哈希码计算存储位置
        int index = hash % table.length;
        //查找对应的Entry
        Entry entry = null;
        if (table[index] != null) {
            Entry currentEntry = table[index];
            while (currentEntry != null) {
                if (currentEntry.getKey().equals(key)) {
                    entry = currentEntry;
                    break;
                }
                currentEntry = currentEntry.next;
            }
        }
        return entry == null ? null : entry.getValue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                Entry entry = table[i];
                while (entry != null) {
                    builder.append(entry.getKey() + "=" + entry.getValue() + ",");
                    entry = entry.next;
                }
            }
        }
        if (builder.length() != 1) {
            builder.deleteCharAt(builder.length() - 1);
        }
        builder.append("}");
        return builder.toString();
    }

    /**
     * 定义内部类 实现map的内部接口
     */
    class Entry implements Map.Entry {
        private int hash;//哈希码
        private Object key;//键
        private Object value;//值
        private Entry next;//指向下一个节点的指针

        //无参构造
        public Entry() {
        }

        //有参构造
        public Entry(int hash, Object key, Object value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        //有参构造
        public Entry(int hash, Object key, Object value, Entry next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public Object getKey() {
            return this.key;
        }

        @Override
        public Object getValue() {
            return this.value;
        }
    }


    /**
     * 测试类
     *
     * @param args
     */
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();

        //key = A和a时，计算出来的存储位置index都为1，会产生hash冲突，从而形成链表结构
        hashMap.put("A", "1");
        hashMap.put("B", "2");
        hashMap.put("a", "3");
    }

}
