package com.test;

import com.nccs.diy.linkedlist.MyLinkedList;

import java.util.LinkedList;


/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-12-10 14:58
 * @description:
 **/

public class Test01 {
    public static void main(String[] args) {
        MyLinkedList<String> list1 = new MyLinkedList<>();
        list1.addFirst("1");
        list1.addFirst("2");
        list1.addFirst("3");
        System.out.println(list1.toString());


        LinkedList linkedList = new LinkedList();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        System.out.println(linkedList.toArray());

    }
}
