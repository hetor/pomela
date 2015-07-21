package com.tangerine.yuzu.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
 
public class ExeInvoker {
 
    public static void main(String[] args) {
        OutputStream os = null;
        InputStream is = null;
        try
        {
            // 打开cmd进程
            Process process = Runtime.getRuntime().exec("D:/ProgramFiles/DP_Installed/NuSMV/2.5.4/bin/NuSMV.exe -load D:/NuSMV_Program/mc-smv.smv");
           
            os = process.getOutputStream();
            is = process.getInputStream();
           
            // 查看cmd版本命令
//            runCommand(os, "cmd /c D:/ProgramFiles/DP_Installed/NuSMV/2.5.4/bin/NuSMV.exe -load D:/NuSMV_Program/mc-smv.smv\n");
            // cd命令
            runCommand(os, "read_model -i 　D:/NuSMV_Program/mc-smv.smv\n");
            // 改变前导符
            runCommand(os, "go\n");
            // 运行批脚本
            runCommand(os, "check_ctlspec\n");
           
            // ......
           
            // 退出
//            runCommand(os, "exit\n");
           
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (true)
            {
                String s = br.readLine();
                if (s == null)
                {
                    break;
                }
                System.out.println(s);
            }
            br.close();
            process.waitFor();
        }
        catch (IOException e) {}
        catch (InterruptedException e) {}
        finally
        {
            try
            {
                if (os != null)
                {
                    os.close();
                }
                if (is != null)
                {
                    is.close();
                }
            }
            catch (IOException e) {}
        }
    }
   
    private static void runCommand(OutputStream os, String command) throws IOException
    {
        os.write(command.getBytes());
        os.flush();
    }
 
}