package com.nccs.diy.linkedlist;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-12-10 16:09
 * @description: 手写实现LinkedList
 **/

public class MyLinkedList<E> {


    private Node dummyHead; //顶级节点

    private int size; //集合长度

    public MyLinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {//在链表中间添加元素
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("索引越界异常");
        }
        Node prev = dummyHead;//定义一个指针指向头结点
        for (int i = 0; i < index; i++) {
            prev = prev.next;//将这个指针移动到要插入的位置的前一个元素
        }
        /*Node node = new Node(e);
        node.next = prev.next;
        prev.next = node;*/    //注意这两行代码的顺序。用下面一行代码实现
        prev.next = new Node(e, prev.next);
        size++;
    }

    public void addFirst(E e) {//在链表头添加元素
        /*Node node = new Node(e);
        node.next = head;
        head = node;*/    //用下面一行代码代替
        //head = new Node(e,head);//括号内的head是之前的链表的头结点，左边的head是现在的头结点
        add(0, e);
    }

    public void addLast(E e) {//在链表的尾部添加元素
        add(size, e);
    }

    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("索引越界异常");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void update(int index, E e) {//修改某个元素
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("索引越界异常");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    public boolean contains(E e) {//查询链表中是否存在某个元素
        Node node = dummyHead.next;
        while (node != null) {
            if (node.e.equals(e)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public void delete(int index) {//删除元素
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("索引越界异常");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        /*prev.next = prev.next.next;//注意！！！ 这是错误的
        prev.next.next = null;*/
        Node cur = prev.next;
        prev.next = cur.next;
        cur.next = null;
        size--;
    }

    public void deleteFirst() {
        delete(0);
    }

    public void deleteLast() {
        delete(size - 1);
    }

    public void deleteElement(E e) {

        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.e.equals(e))
                break;
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
        }
    }


    private class Node {
        public E e;//元素
        public Node next;//指针

        public Node(E e, Node next) {//传入元素和指针
            this.e = e;
            this.next = next;
        }

        public Node() {//不传入元素和指针
            this(null, null);//this是传入两个参数的构造器
        }

        public Node(E e) {
            this(e, null);
        }

        @Override
        public String toString() {
            return "Node [e=" + e + "]";
        }
    }

}
