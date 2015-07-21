package org.pomela.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Copyright (c) 2014, All Rights Reserved. 
 *
 * 源端：FileReader 
 * 装饰器类：BufferedReader 
 *
 * @Author hetor, tao.he1989@gmail.com
 * @Date Jun 21, 2014 7:04:08 PM
 * @Since JDK1.7
 * @version 1.0.0
 */
public class BufferedInputFile {
    
    /**
     * @param filename
     * @return "" if error
     */
    public static String read(String filename) {
        BufferedReader bufferedReader = null;
        File file = new File(filename);
        StringBuilder sb = new StringBuilder();;
        try {
            bufferedReader = new BufferedReader( new FileReader(file));
            String line;
            while(null != (line = bufferedReader.readLine())) {
                sb.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
       System.out.println(read("properties/nio-data.txt"));
    }
}
