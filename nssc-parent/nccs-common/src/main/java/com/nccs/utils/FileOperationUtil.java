package com.nccs.utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
//import java.nio.channels.FileChannel;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-23 09:54
 * @description: 使用三种方式进行文件读写操作，测试性能
 * <p>
 * 测试文件大小：3,840,416,650 字节（3.57G）
 **/

public class FileOperationUtil {

    /**
     * 使用文件映射进行读写
     *
     * @param oldPath
     * @param newPath
     * @throws IOException
     */
    public static void copyFileMapped(String oldPath, String newPath) throws IOException {
        long length = 0;
        RandomAccessFile readRandom = new RandomAccessFile(oldPath, "r");
        FileChannel readChannel = readRandom.getChannel();
        length = readChannel.size();
        //返回要读取文件的映射内存区块
        MappedByteBuffer readMapped = readChannel.map(FileChannel.MapMode.READ_ONLY, 0, length);

        //此处如果length > Integer.MAX_VALUE ，则会抛出异常
        ByteBuffer readBuffer = readMapped.get(new byte[(int) length]);

        //要写入的文件
        RandomAccessFile writeRandom = new RandomAccessFile(newPath, "rw");
        FileChannel writeChannel = writeRandom.getChannel();
        MappedByteBuffer writeMapped = writeChannel.map(FileChannel.MapMode.READ_WRITE, 0, length);
        for (int i = 0; i < length; i++) {
            writeMapped.put(i, readBuffer.get(i));
        }
        writeChannel.close();
        readChannel.close();
        readRandom.close();
        writeRandom.close();
        /**
         * MappedByteBuffer是java平台共享内存的实现，把硬盘虚拟为内存，
         * 主要用于进程间共享数据，所以在进程没有退出前文件是不允许删除的。
         * 一个映射的字节缓冲区和文件映射，它代表仍然有效，直到缓冲本身是垃圾收集。
         */
        readRandom = null;
        writeRandom = null;
        System.gc();
        try {
            //等待垃圾回收
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用文件通道进行读写
     * 读写超大文件时，缓冲区设置的越大，消耗时间越短。但是也不能设置过大，否则抛出异常。根据实际情况合理设置
     *
     * @param oldPath
     * @param newPath
     * @throws IOException
     */
    public static void copyFileNIO(String oldPath, String newPath) throws IOException {
        FileInputStream fis = new FileInputStream(oldPath);
        FileOutputStream fos = new FileOutputStream(newPath);
        //获取输入输出通道
        FileChannel fcin = fis.getChannel();
        FileChannel fout = fos.getChannel();
        //创建缓冲区，读写超大文件时，缓冲区设置的越大，消耗时间越短。但是也不能设置过大，否则抛出异常。根据实际情况合理设置
        ByteBuffer buffer = ByteBuffer.allocate(1024*100);
        while (true) {
            //clear方法重设缓冲区，使它可以接受读入的数据
            buffer.clear();
            //读取文件
            int len = fcin.read(buffer);
            if (len == -1) {
                break;
            }
            //写模式转换成读模式。该限制设置为当前的位置然后位置设置为零。如果标记定义然后丢弃。
            //flip方法让缓冲区可以将新读入的数据写入另一个通道
            buffer.flip();
            fout.write(buffer);
        }
        fcin.close();
        fout.close();
    }

    /**
     * 使用BufferedInputStream进行读写
     *
     * @param oldPath
     * @param newPath
     * @throws IOException
     */
    public static void copyFile(String oldPath, String newPath) throws IOException {
        FileInputStream fis = new FileInputStream(oldPath);
        FileOutputStream fos = new FileOutputStream(newPath);

        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        byte[] buffer = new byte[1024];

        int len = 0;
        while ((len = bis.read(buffer)) != -1) {
            bos.write(buffer, 0, buffer.length);
        }
        bis.close();
        bos.close();
    }


    //测试方法
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            //方法一：使用文件映射，文件大小超过Integer.MAX_VALUE后会抛异常
//            copyFileMapped("D:/test.zip", "D:/test01.zip");

            //方法二：使用文件通道，超大文件不会抛异常。但效率较低（如果文件特别大，可以适当调大缓冲区的大小，可以减少写入时间）
            copyFileNIO("D:/test.zip", "D:/test01.zip");

            //方法三：使用BufferedInputStream (22351毫秒)
//            copyFile("D:/test.zip", "D:/test01.zip");

            System.out.println("共耗时：" + (System.currentTimeMillis() - start) + " 毫秒");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
