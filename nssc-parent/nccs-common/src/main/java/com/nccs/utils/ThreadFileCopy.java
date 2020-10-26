//package com.nccs.utils;
//
//import com.sun.org.apache.xml.internal.utils.SerializableLocatorImpl;
//
//import javax.jws.soap.SOAPBinding;
//import java.io.*;
//import java.lang.reflect.Method;
//import java.nio.MappedByteBuffer;
//import java.nio.channels.FileChannel;
//import java.nio.channels.FileLock;
//import java.nio.channels.OverlappingFileLockException;
//import java.security.AccessController;
//import java.security.PrivilegedAction;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//
///**
// * @program: nssc-parent
// * @author: xuzengsheng
// * @create: 2020-10-23 16:32
// * @description: 使用多线程进行文件复制
// **/
//
//public class ThreadFileCopy {
//
//    public static void main(String... args) throws IOException {
//
//        String fileName = "D://test.zip";
////        RandomAccessFile readAccessFile = new RandomAccessFile(fileName, "rw");
////        FileChannel readChannel = readAccessFile.getChannel();
//        FileInputStream fileInputStream = new FileInputStream(new File(fileName));
//
////        long fileSize = readChannel.size(); //文件大小
//
//        long fileSize = fileInputStream.getChannel().size();
//        System.out.println("len: " + fileSize);
//
//        int size = 1024 * 100; //单次读取的数据量大小
//        //计算批次  总批次 = 总大小 / 最大单次大小
//        int batchSize = (int) Math.ceil((double) fileSize / (double) size);
//
//
//        //创建对象集合
//        List<ThreadFileCopy.FileBlockWriter> writers = new ArrayList<ThreadFileCopy.FileBlockWriter>();
//
//        //定义计数器，判断任务是否启动
//        CountDownLatch startLatch = new CountDownLatch(1);
//        //计数器，判断所有线程是否就绪
//        CountDownLatch endLatch = new CountDownLatch(batchSize);
//
//        //循环批次，创建线程
//        int from = 0;//写入文件的起始点（从0开始，每个线程写入size长度的文件数据）
//        int sliceLen = size;  //写入文件的结束点
//        for (int i = 0; i < batchSize; i++) {
//            ThreadFileCopy.FileBlockWriter writer = null;
//            from = i * size;
//            System.out.println(Thread.currentThread().getName() + "写入的起始点：" + from);
//
//            if ((fileSize - from) < size) {
//                sliceLen = (int) fileSize - from;// 最后一片区域的大小
//            }
//            ByteArrayOutputStream bos = new ByteArrayOutputStream(sliceLen);
//            int n; //从文件中读取到的字节
//            if ((n = fileInputStream.read(new byte[sliceLen])) != -1) {
//                bos.write(new byte[sliceLen], 0, n);
//            }
//            byte[] slice = bos.toByteArray();
//            bos.close();
//            bos = null;
//            System.out.println(Thread.currentThread().getName() + "写入的结束点：" + (from + sliceLen));
////            System.arraycopy(, from, slice, 0, sliceLen);
//
//            String newFile = "D://test03.zip";
//            writer = new ThreadFileCopy.FileBlockWriter(newFile, from, sliceLen, slice, startLatch, endLatch);
//            from += sliceLen;// 记录本次映射区域的大小，作为下一片区域的开始
//
//            writers.add(writer);
//        }
//
//        for (ThreadFileCopy.FileBlockWriter writer : writers) {
//            writer.start();
//        }
//
//        //调用countDown后，所有写入线程才真正开始写入工作
//        startLatch.countDown();
//
//        //等待所有线程写入完成
//        await(endLatch);
//
//        System.out.println("done !!! ");
//
//
//    }
//
//
//    public static void await(CountDownLatch latch) {
//        if (latch == null) {
//            return;
//        }
//
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static File getByName(String path) {
//
//        File file = new File(path);
//
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                throw new IllegalArgumentException("create file failed", e);
//            }
//        }
//
//        if (file.isDirectory()) {
//            throw new IllegalArgumentException("not a file");
//        }
//
//        return file;
//    }
//
//
//    /**
//     * 该线程类负责将指定的内容(contents)写入文件（target）,
//     * 写入的起始位置是：from，往后写的字节数目是：length
//     */
//    public static class FileBlockWriter extends Thread {
//
//        private String target;
//        private int from;
//        private int length;
//        private byte[] contents;
//        private CountDownLatch start;
//        private CountDownLatch end;
//
//        public FileBlockWriter(String target, int from, int length, byte[] contents, CountDownLatch start, CountDownLatch end) {
//            this.target = target;
//            this.from = from;
//            this.length = length;
//            this.contents = contents;
//            this.start = start;
//            this.end = end;
//        }
//
//        @Override
//        public void run() {
//
//            RandomAccessFile randFile = null;
//            FileChannel channel = null;
//            MappedByteBuffer mbb = null;
//            FileLock fileLock = null;
//
//            ThreadFileCopy.await(start);
//
//            try {
//
//                randFile = new RandomAccessFile(target, "rw");
//                channel = randFile.getChannel();
//                mbb = channel.map(FileChannel.MapMode.READ_WRITE, from, length);
//
//                fileLock = channel.lock(from, length, true);
//
//                while (fileLock == null || !fileLock.isValid()) {
//                    fileLock = channel.lock(from, length, true);
//                    System.out.print("锁无效，重复获取");
//                }
//                mbb.put(contents);
//                mbb.force();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (OverlappingFileLockException e) {
//                e.printStackTrace();
//                throw new IllegalArgumentException("程序设计不合理，加锁区域相互重叠");
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//
//                release(fileLock);
//                forceClose(mbb);
//                close(channel, randFile);
//            }
//            end.countDown();
//        }
//    }
//
//
//    private static void release(FileLock fileLock) {
//        if (fileLock == null) {
//            return;
//        }
//        try {
//            fileLock.release();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void close(Closeable... closeables) {
//        if (closeables == null || closeables.length == 0) {
//            return;
//        }
//
//        for (Closeable closeable : closeables) {
//            if (closeable == null) {
//                continue;
//            }
//            try {
//                closeable.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 强制关闭MappedByteBuffer
//     *
//     * @param mbb
//     */
//    private static void forceClose(MappedByteBuffer mbb) {
//        // 用特权访问方式访问
//        AccessController.doPrivileged(new PrivilegedAction() {
//            public Object run() {
//                try {
//                    Method getCleanerMethod = mbb.getClass().getMethod("cleaner", new Class[0]);
//                    getCleanerMethod.setAccessible(true);
//                    sun.misc.Cleaner cleaner = (sun.misc.Cleaner)
//                            getCleanerMethod.invoke(mbb, new Object[0]);
//                    cleaner.clean();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        });
//    }
//
//
//}
