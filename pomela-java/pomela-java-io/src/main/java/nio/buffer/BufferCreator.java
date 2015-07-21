package nio.buffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by hetor on 15/6/4.
 */
public class BufferCreator {
    public static void main(String[] args) throws Exception {
        //分配指定大小的缓冲区
        ByteBuffer buffer1 = ByteBuffer.allocate(10);

        //包装一个现有的数组
        byte[] datas = new byte[10];
        ByteBuffer buffer2 = ByteBuffer.wrap(datas);

        //缓冲区分片，在缓冲区上创建子缓冲区，子缓冲区与现有缓冲区在底层数组层面上是数据共享
        ByteBuffer buffer3 = ByteBuffer.allocate(10);

        for(int i=0; i<buffer3.capacity(); i++) {
            buffer3.put((byte)i);
        }

        buffer3.position(3);
        buffer3.limit(7);
        ByteBuffer subBuffer3 = buffer3.slice();

        for(int i=0; i<subBuffer3.capacity(); i++) {
            byte b = subBuffer3.get(i);
            b *= 10;
            subBuffer3.put(i, b);
        }

        buffer3.position(0);
        buffer3.limit(buffer3.capacity());

        while(buffer3.hasRemaining()) {
            System.out.println(buffer3.get());
        }

        //只读缓冲区 将任何缓冲区转化为只读缓冲区 并与原缓冲区共享数据 只不过他是只读的
        ByteBuffer buffer4 = ByteBuffer.allocate(10);

        for(int i=0; i<buffer4.capacity(); i++) {
            buffer4.put((byte)i);
        }

        ByteBuffer readOnlyBuffer4 = buffer4.asReadOnlyBuffer();

        for(int i=0; i<buffer4.capacity(); i++) {
            byte b = buffer4.get(i);
            b *= 10;
            buffer4.put(i, b);
        }

        readOnlyBuffer4.position(0);
        readOnlyBuffer4.limit(readOnlyBuffer4.capacity());

        while(readOnlyBuffer4.hasRemaining()) {
            System.out.println(readOnlyBuffer4.get());
        }

        //直接缓冲区
        FileInputStream fin = new FileInputStream("properties/nio-data.txt");
        FileChannel fcin = fin.getChannel();

        FileOutputStream fout = new FileOutputStream("properties/nio-data-to.txt");
        FileChannel fcout = fout.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while (true) {
            buffer.clear();
            int r = fcin.read(buffer);
            if (r == -1) {
                break;
            }
            buffer.flip();
            fcout.write(buffer);
        }

        //内存映射文件I/O
        RandomAccessFile raf = new RandomAccessFile("properties/nio-data.txt", "rw");
        FileChannel fc = raf.getChannel();

        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, 1024);

        mbb.put(0, (byte)97);
        mbb.put(1023, (byte)122);

        raf.close();
    }
}
