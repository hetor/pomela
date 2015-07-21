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
            // ��cmd����
            Process process = Runtime.getRuntime().exec("D:/ProgramFiles/DP_Installed/NuSMV/2.5.4/bin/NuSMV.exe -load D:/NuSMV_Program/mc-smv.smv");
           
            os = process.getOutputStream();
            is = process.getInputStream();
           
            // �鿴cmd�汾����
//            runCommand(os, "cmd /c D:/ProgramFiles/DP_Installed/NuSMV/2.5.4/bin/NuSMV.exe -load D:/NuSMV_Program/mc-smv.smv\n");
            // cd����
            runCommand(os, "read_model -i ��D:/NuSMV_Program/mc-smv.smv\n");
            // �ı�ǰ����
            runCommand(os, "go\n");
            // �������ű�
            runCommand(os, "check_ctlspec\n");
           
            // ......
           
            // �˳�
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