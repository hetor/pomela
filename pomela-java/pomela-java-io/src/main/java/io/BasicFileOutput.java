package org.pomela.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

public class BasicFileOutput {

    private final static String FILE = "properties/BasicFileOutput.out";
    
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(
                new StringReader(
                        BufferedInputFile.read("properties/nio-data.txt")));
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(FILE)));
            //writer = new PrintWriter(FILE); is the same
            int lineCount = 1;
            String line;
            while(null != (line = reader.readLine())) {
                writer.println(lineCount++ + "：" + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != writer) {
                writer.close();
            }
        }
    }
}
