package com.nccs.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-23 08:59
 * @description: 使用分批次映射读取超大文件
 **/

public class MappedBiggerFileReader {
    private MappedByteBuffer[] mappedBufArray; //内存文件映射数组（分批次映射）
    private int currentCount = 0;  //计数器 记录已完成映射的批次
    private int totalBatchSize;  //总批次数
    private FileInputStream fileIn; //文件输入流
    private FileOutputStream fileOut; //文件输出流
    private long fileLength;    //文件大小
    private int byteBatchSize;  //单批次最大映射大小
    private byte[] bytes; //当前批次的映射的数据集合

    public MappedBiggerFileReader(String fileName, int arraySize) throws IOException {

        this.byteBatchSize = arraySize;

        this.fileIn = new FileInputStream(fileName); //读取文件
        FileChannel fileChannel = fileIn.getChannel(); //创建文件通道
        this.fileLength = fileChannel.size(); //获取文件的总大小

        //定义批次  总批次 = 总大小 / 最大单次大小
        this.totalBatchSize = (int) Math.ceil((double) fileLength / (double) Integer.MAX_VALUE);

        this.mappedBufArray = new MappedByteBuffer[totalBatchSize];// 初始化内存文件映射数组
        long preLength = 0;//当前映射位置
        long regionSize = (long) Integer.MAX_VALUE;// 映射区域的大小
        for (int i = 0; i < totalBatchSize; i++) {// 将文件的连续区域映射到内存文件映射数组中
            if (fileLength - preLength < (long) Integer.MAX_VALUE) {
                regionSize = fileLength - preLength;// 最后一片区域的大小
            }
            //进行文件映射，从开始位置，映射区域大小
            mappedBufArray[i] = fileChannel.map(FileChannel.MapMode.READ_WRITE, preLength, regionSize);
            preLength += regionSize;// 记录本次映射区域的大小，作为下一片区域的开始
        }

    }

    public int read() throws IOException {
        if (currentCount >= totalBatchSize) { //判断
            return -1;
        }
        int limit = mappedBufArray[currentCount].limit();
        int position = mappedBufArray[currentCount].position();
        if (limit - position > byteBatchSize) {
            bytes = new byte[byteBatchSize];
            mappedBufArray[currentCount].get(bytes);
            return byteBatchSize;
        } else {// 本内存文件映射最后一次读取数据
            bytes = new byte[limit - position];
            mappedBufArray[currentCount].get(bytes);
            if (currentCount < totalBatchSize) {
                currentCount++;// 转换到下一个内存文件映射
            }
            return limit - position;
        }
    }

    public void close() throws IOException {
        fileIn.close();
        bytes = null;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public long getFileLength() {
        return fileLength;
    }

    public static void main(String[] args) throws IOException {
        MappedBiggerFileReader reader = new MappedBiggerFileReader("D:/test.zip", 65536);
        long start = System.currentTimeMillis();
        while (reader.read() != -1) ;
        long end = System.currentTimeMillis();
        reader.close();
        System.out.println("总耗时: " + (end - start));
    }
}
