package org.pomela.io;

import java.io.IOException;
import java.io.StringReader;

public class MemoryInput {
    public static void main(String[] args) {
        StringReader reader = new StringReader(
                BufferedInputFile.read("properties/nio-data.txt"));
        int ch;
        try {
            while(-1 != (ch = reader.read())) {
                System.out.print((char)ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
