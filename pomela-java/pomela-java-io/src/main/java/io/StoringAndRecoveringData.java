package org.pomela.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StoringAndRecoveringData {

    public static void main(String[] args) {
        try {
            DataOutputStream out = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream("properties/Data.txt")));
            out.writeDouble(3.14159D);
            out.writeUTF("如果我们使用DataOutputStream写入数据，Java保证我们可以使用DatainputStream准确地读取数据");
            out.writeDouble(1.41413);
            out.writeUTF("——无论读和写数据的平台多么不同");
            out.close();
            
            DataInputStream in = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream("properties/Data.txt")));
            System.out.println(in.readDouble());
            System.out.println(in.readUTF());
            System.out.println(in.readDouble());
            System.out.println(in.readUTF());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
