package org.pomela.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class UsingRandomAccessFile {
    private final static String FILE = "properties/rtest.dat";
    static void display() {
        try {
            RandomAccessFile rf = new RandomAccessFile(FILE, "r");
            for(int i=0; i<7; i++) {
                System.out.println("Value " + i + ": " + rf.readDouble());
            }
            System.out.println(rf.readUTF());
            rf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        try {
            RandomAccessFile rf = new RandomAccessFile(FILE, "rw");
            for(int i=0; i<7; i++) {
                rf.writeDouble(i*1.414);
            }
            rf.writeUTF("The end of the file");
            rf.close();
            display();
            rf = new RandomAccessFile(FILE, "rw");
            rf.seek(5*8);
            rf.writeDouble(47.000001);
            rf.close();
            display();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
