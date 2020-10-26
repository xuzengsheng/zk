package com.nccs.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-23 10:18
 * @description: 超大文件读写操作类
 **/

public class BigFileIOUtil {
    private MappedByteBuffer[] mappedBufArray; //内存文件映射数组（分批次映射）
    private int currentCount = 0;  //计数器 记录已完成映射的批次
    private int totalBatchSize;  //总批次数
    private FileInputStream fileIn; //文件输入流
    private FileOutputStream fileOut; //文件输出流
    private long fileLength;    //文件大小
    private int byteBatchSize;  //单批次最大映射大小
    private byte[] bytes; //当前批次的映射的数据集合


    public BigFileIOUtil(String oldFileName, int byteBatchSize) throws IOException {
        this.byteBatchSize = byteBatchSize;
        RandomAccessFile randomRead = new RandomAccessFile(oldFileName, "rw");//读取文件
        FileChannel fileChannel = randomRead.getChannel(); //创建文件通道
        this.fileLength = fileChannel.size(); //获取文件的总大小
        //计算批次  总批次 = 总大小 / 最大单次大小
        this.totalBatchSize = (int) Math.ceil((double) fileLength / (double) Integer.MAX_VALUE);
        this.mappedBufArray = new MappedByteBuffer[totalBatchSize];// 初始化内存文件映射数组

        long preLength = 0;//上次映射的文件长度
        long regionSize = (long) Integer.MAX_VALUE;// 映射区域的大小，（每次最大只能映射Integer.MAX_VALUE大小的文件，超过这个文件大小会抛出异常）
        for (int i = 0; i < totalBatchSize; i++) {// 将文件分批次映射到文件数组中
            //3.判断是否为最后一批次数据
            //最后一批次的数据大小肯定 <= Integer.MAX_VALUE
            if (fileLength - preLength < (long) Integer.MAX_VALUE) {
                regionSize = fileLength - preLength;// 最后一片区域的大小
            }
            //1.进行文件映射
            //从preLength = 0开始，每次映射Integer.MAX_VALUE大小的文件，并记录已经映射的文件大小，下一次映射从（preLength + Integer.MAX_VALUE）的位置开始
            mappedBufArray[i] = fileChannel.map(FileChannel.MapMode.READ_WRITE, preLength, regionSize);
            //2.记录已映射的文件位置
            preLength += regionSize;// 记录本次映射区域的大小，作为下一片区域的开始
        }
        fileChannel.close();
        randomRead = null;

    }

    //读操作
    public int read() {
        if (currentCount >= totalBatchSize) { //如果已处理完成的批次 >= 总批次，则退出
            return -1;
        }
        // 步骤1：获取limit。limit为当前批次的缓冲区的当前终点，不能对缓冲区超过极限的位置进行读写操作。且极限是可以修改的
        // 根据初始化的批次大小，
        // 第一个缓冲区的终点值应该为 Integer.MAX_VALUE ，
        // 第二个缓冲区的终点值应该为 Integer.MAX_VALUE * 2 ，
        // 第三个缓冲区的终点值应该为 Integer.MAX_VALUE * 3 ，依次类推（最后一个除外）
        int limit = mappedBufArray[currentCount].limit();

        // 步骤2：获取position。 position为下一个要被读或写的元素的索引，每次读写缓冲区数据时都会改变改值，为下次读写作准备
        // 因为是从头开始读，所以position从0开始，然后每次读取 byteBatchSize个长度的数据，每读取一次，position的大小增加相应的byteBatchSize的长度
        int position = mappedBufArray[currentCount].position();

        //步骤3：判断当前缓冲区是否还能读取byteBatchSize长度的数据（不能读取超过缓冲区大小的数据）
        if (limit - position >= byteBatchSize) {
            //步骤3.1：如果能够读取，则读取position大小的数据
            bytes = new byte[byteBatchSize];
            mappedBufArray[currentCount].get(bytes);
            return byteBatchSize; //此处返回值没有特别含义，只要不返回 -1即可（-1表示读取完成）
        } else {
            //步骤3.2：如果当前缓冲区能读取的数据长度小于byteBatchSize，表示为该缓冲区最后一次读取数据
            bytes = new byte[limit - position];
            mappedBufArray[currentCount].get(bytes);

            //步骤4：判断是否需要转到下一个缓冲区，currentCount从0开始，当前缓冲区完成后，currentCount + 1，直到读完最后一个缓冲区为止
            if (currentCount < totalBatchSize) {
                currentCount++;// 转换到下一个内存文件映射
            }
            return limit - position;//此处返回值没有特别含义，只要不返回 -1即可（-1表示读取完成）
        }
    }

    //写操作
    public void write() throws IOException {
        RandomAccessFile randomWrite = new RandomAccessFile("D:/test02.zip", "rw");
        FileChannel fileOutChannel = randomWrite.getChannel();
//        long preLength = 0;//上次映射的文件长度
//        long regionSize = (long) Integer.MAX_VALUE;// 映射区域的大小，（每次最大只能映射Integer.MAX_VALUE大小的文件，超过这个文件大小会抛出异常）
        for (MappedByteBuffer buffer : this.mappedBufArray) {
            MappedByteBuffer byteBuffer = fileOutChannel.map(FileChannel.MapMode.READ_WRITE, buffer.position(), buffer.limit());
            byteBuffer.put(buffer);
            byteBuffer.clear();
        }
        fileOutChannel.close();
        randomWrite = null;
    }

    public void copy() throws IOException {
        this.read();
        this.write();
    }


    public static void main(String[] args) throws IOException {
        System.out.println("文件复制开始...");
        BigFileIOUtil util = new BigFileIOUtil("D:/test.zip", 65536);
        long start = System.currentTimeMillis();
        util.copy();
        long end = System.currentTimeMillis();
        System.out.println("文件复制完成，共耗时： " + (end - start) + " 毫秒");

    }

}
