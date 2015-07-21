package com.tangerine.yuzu.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
    
public class ExeInvokerBat {

    private static String batRealPath = ExeInvokerBat.class.getResource("/1.bat").getPath();

    public static void main(String[] args) {

        System.out.println(batRealPath);

        Runtime run = Runtime.getRuntime();
        try {
            Process p = run.exec("cmd.exe /k start " + "C:/Users/hetor/workspace/yuzu/bin/1.bat");

            BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String msg = null;
            while ((msg = bf.readLine()) != null) {
                System.out.println(msg);
            }
        } catch (IOException e) {}
    }
}
