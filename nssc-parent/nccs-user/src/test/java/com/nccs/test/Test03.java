package com.nccs.test;

import javafx.beans.binding.When;
import rx.internal.operators.OnSubscribeTakeLastOne;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-22 16:42
 * @description:
 **/

public class Test03 {
    public static void main(String[] args) {
        test01();
        test02();
    }


    /**
     * 使用BufferedInputStream、BufferedOutputStream（缓存字节流）进行文件的读写操作 （效率高，推荐使用）
     */
    public static void test01() {
        try {
            //判断何时使用输入流，何时使用输出流
            //1.遵循一个原则：所有输入输出都是相对于内存来讲的
            //2.输入流：以内存为中心，将文件从磁盘读取到内存，字节流的方向  磁盘 --> 内存，即将字节从磁盘加载到内存中，所以用输入流
            //3.输出流：以内存为中心，将文件写入到磁盘，字节流的方向 内存 --> 磁盘。即将字节从内存中取出，写入到磁盘中，所以用输出流


            //输入流，将文件读取出来输入到内存中
            BufferedInputStream bin = new BufferedInputStream(new FileInputStream(new File("D:/MyData/ex_xuzc/Desktop/个人文件夹/堡垒机日志查看路径.txt")));

            //输出流，将读取出来的内容从内存中输出到磁盘中
            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(new File("D:/MyData/ex_xuzc/Desktop/个人文件夹/test1.txt")));
            byte[] bytes = new byte[1024]; //一次性读取字节数
            int n = -1; //记录读取到的文件内容，文件内容为一个int类型的二进制数
            while ((n = bin.read(bytes, 0, bytes.length)) != -1) {
                String str = new String(bytes, 0, n, "utf-8");
                System.out.println(str);
                bout.write(bytes, 0, n);
            }

            bout.close();
            bin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * BufferedReader、BufferedWriter(缓存流，提供readLine方法读取一行文本)
     */
    public static void test02() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/MyData/ex_xuzc/Desktop/个人文件夹/堡垒机日志查看路径.txt"), "UTF-8"));

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/MyData/ex_xuzc/Desktop/个人文件夹/test2.txt"), "UTF-8"));


            String str = null; //记录读取到的数据

            while ((str = bufferedReader.readLine()) != null) {
                System.out.println(str);
                bufferedWriter.write(str);
                bufferedWriter.newLine();
            }

            //清除缓存
            bufferedWriter.flush();
            bufferedWriter.close();
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 使用文件通道的方式进行分批读取文件
     */
    public static void test03() {

        try {
            FileInputStream fileInputStream = new FileInputStream("D:/MyData/ex_xuzc/Desktop/个人文件夹/堡垒机日志查看路径.txt");
            FileChannel fileChannel = fileInputStream.getChannel();
            ByteBuffer allocate = ByteBuffer.allocate(1000);
            int read = fileChannel.read(allocate); //读取到bufferbyte中
            byte[] array = null;
            if (read != -1) {
                array = new byte[read]; // 字节数组长度为已读取长度
                allocate.flip();
                ByteBuffer byteBuffer = allocate.get(array);// 从ByteBuffer中得到字节数组
                allocate.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
