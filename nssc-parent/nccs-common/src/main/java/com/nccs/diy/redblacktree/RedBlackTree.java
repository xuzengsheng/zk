package com.nccs.diy.redblacktree;


/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-20 17:27
 * @description: 自定义红黑树
 * 在put的过程中，使用Comparable<T>中的compareTo来比较两个元素的大小的，所以在二叉树中存储的元素必须要继承Comparable<T> 类，覆写compareTo方法。
 **/
public class RedBlackTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root; //根节点
    private int size;  //元素长度

    //无参构造
    public RedBlackTree() {
        root = null;
        size = 0;
    }


    //获取元素长度
    public int getSize() {
        return size;
    }

    //判断所有节点是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断节点node的颜色，所有空节点都是黑色
     *
     * @param node
     * @return
     */
    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.isRed;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node) {
        Node x = node.right;

        //左旋转
        node.right = x.left;
        x.left = node;

        x.isRed = node.isRed;
        node.isRed = RED;

        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node) {
        Node x = node.left;

        //右旋转
        node.left = x.right;
        x.right = node;

        x.isRed = node.isRed;
        node.isRed = RED;

        return x;
    }

    /**
     * 颜色翻转
     *
     * @param node
     */
    private void flipColors(Node node) {
        node.isRed = RED;
        node.left.isRed = BLACK;
        node.right.isRed = BLACK;
    }

    /**
     * 向红黑树中添加新的元素(key,value)
     *
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        root = add(root, key, value);
        root.isRed = BLACK;
    }

    /**
     * 向以node为根的红黑树中插入元素(key,value),递归算法
     *
     * @param node
     * @param key
     * @param value
     * @return 返回插入新节点后红黑树的根
     */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);//默认插入红色节点
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }

        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }

        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key) {

        if (node == null)
            return null;

        if (key.equals(node.key))
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }


    /**
     * 内部类 》》 节点
     */
    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public boolean isRed;

        //有参构造
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            isRed = RED;
        }
    }


    public static void main(String[] args) {
        RedBlackTree<String, String> tree = new RedBlackTree<>();
        tree.add("A","1");
        tree.add("B","2");
        tree.add("a","3");
        tree.add("c","4");
        tree.add("d","5");
        tree.add("e","6");
        tree.add("f","7");
        tree.add("g","8");
        tree.add("h","9");
        tree.add("i","10");
        tree.add("j","11");
        tree.add("k","12");
        tree.add("l","13");
    }

}

