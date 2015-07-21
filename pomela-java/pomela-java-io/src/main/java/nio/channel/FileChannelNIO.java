package nio.channel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

public class FileChannelNIO {

	public static void readFromFile() throws Exception {
		RandomAccessFile aFile = new RandomAccessFile("properties/nio-data.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(48);

		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {
			System.out.println("The number of bytes read " + bytesRead);

			buf.flip();

			while(buf.hasRemaining()){
				CharBuffer charBuffer = buf.asCharBuffer();
				System.out.println(charBuffer.get());
				System.out.print((char) buf.get());
			}

			buf.clear();

			bytesRead = inChannel.read(buf);
		}
		aFile.close();
	}

	public static void writeToFile() throws Exception {
		final byte message[] = { 83, 111, 109, 101, 32, 98, 121, 116, 101, 115, 46 };
		FileOutputStream fout = new FileOutputStream("properties/nio-data.txt");
		FileChannel fc = fout.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		for (int i=0; i<message.length; ++i) {
			buffer.put( message[i] );
		}

		buffer.flip();
		fc.write(buffer);
		fout.close();
	}

	public static void main(String[] args) throws IOException{

	}
}
