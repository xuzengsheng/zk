package com.nccs.diy.arraylist;

import java.util.Objects;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-12-10 15:05
 * @description: 手写实现ArrayList
 **/

public class MyArrayList {
    private Object[] elementData;//一个object数组
    private int size;//大小

    /**
     * 无参构造 默认数组长度10
     */
    public MyArrayList() {
        this(10);//初始大小为10
    }

    /**
     * 有参构造
     *
     * @param initCapacity 指定数组长度
     */
    public MyArrayList(int initCapacity) {
        elementData = new Object[initCapacity];
    }

    /**
     * 获取集合长度
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 判断是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取指定位置的元素
     *
     * @param index
     * @return
     */
    public Object get(int index) {
        /*
         * 1.获取指定下标的对象
         * 2.下标合法性检测
         */
        rangeCheck(index);
        return elementData[index];
    }

    /**
     * 添加元素（不指定位置）
     * 默认在末尾添加元素
     *
     * @param obj
     * @return
     */
    public boolean add(Object obj) {
        ensureCapacity(); //1.检查集合容量
        elementData[size] = obj; //2.将元素添加到集合末尾
        size++; //集合长度+1
        return true;
    }

    /**
     * 在指定位置添加元素
     *
     * @param index
     * @param obj
     */
    public void add(int index, Object obj) {
        /*
         * 插入操作（指定位置）
         * 1.下标合法性检查
         * 2.数组容量检查、扩容
         * 3.数组复制（原数组，开始下标，目的数组，开始下标，长度）
         */
        rangeCheck(index);
        ensureCapacity();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = obj;
        size++;
    }

    /**
     * 删除指定位置元素
     *
     * @param index
     * @return
     */
    public Object remove(int index) {
        /*
         * 1.删除指定下标对象，并返回其值
         * 2.下标合法性检测
         * 3.通过数组复制实现
         * 4.因为前移，数组最后一位要置为空
         */
        rangeCheck(index);
        int arrnums = size - index - 1;
        Object oldValue = elementData[index];
        if (arrnums > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, arrnums);
        }
        elementData[--size] = null;
        return oldValue;
    }

    /**
     * 删除指定元素
     *
     * @param obj
     * @return
     */
    public boolean remove(Object obj) {
        /*
         * 1.删除指定对象
         * 2.通过遍历
         * 3.equals的底层运用，找到下标，调用remove(int i)
         */
        for (int i = 0; i < size; i++) {
            if (get(i).equals(obj)) {         //注意底层用的是equals不是“==”
                remove(i);
                break;
            }
        }
        return true;
    }


    /**
     * 修改指定位置的元素
     *
     * @param index
     * @param obj
     * @return
     */
    public Object set(int index, Object obj) {
        /*
         * 1.将指定下标的对象改变
         * 2.下标合法性检查
         * 3.直接通过数组的赋值来实现改变
         * 4.返回旧值
         */
        rangeCheck(index);
        Object oldValue = elementData[index];
        elementData[index] = obj;
        return oldValue;
    }

    public int indexOf(Object obj) {//查询元素第一次出现的位置
        //ArrayList中的元素可以为null，如果为null返回null的下标
        for (int i = 0; i < size; i++)
            if (Objects.equals(obj, elementData[i]))
                return i;
        //如果没有找到对应的元素返回-1。
        return -1;
    }

    public int lastIndexOf(Object obj) {//查询元素最后一次出现的位置
        for (int i = size - 1; i >= 0; i--)
            if (Objects.equals(obj, elementData[i]))
                return i;
        return -1;
    }


    /**
     * 对下标的检查
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            try {
                throw new Exception("数组小标越界");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 1.对容器容量的检查
     * 2.数组扩容，通过数组复制来实现（量和值两者都要保障）
     */
    private void ensureCapacity() {
        if (size == elementData.length) {
            //如果集合长度等于元素个数 ，则将数组长度扩容为2倍
            Object[] newArray = new Object[size * 2 + 1];
            //将原数组内的元素复制到扩容后的数组中
            System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            //并将新数组赋值给原数组
            elementData = newArray;
        }
    }


}
