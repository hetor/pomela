package org.pomela.io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class FormattedMemoryInput {

    public static void main(String[] args) {
        DataInputStream in = new DataInputStream(
                new ByteArrayInputStream(
                        BufferedInputFile.read("properties/nio-data.txt").getBytes()));
        try {
            //available的工作方式会随着所读取的媒介类型的不同而有所不同，
            //字面意思是在没有组赛的情况下所能读取的字节数
            //要谨慎使用
            //通过捕获EOFException来判断被认为是对异常特性的错误使用
            while(0 != in.available()) {
                System.out.print((char)in.readByte());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
