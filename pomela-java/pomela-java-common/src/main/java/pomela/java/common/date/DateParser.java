package pomela.java.common.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateParser {
    public static void main(String[] args) throws Exception{
        //Default Locale
        SimpleDateFormat formatDefault0 = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
        SimpleDateFormat formatDefault1 = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss");
        //US
        SimpleDateFormat formatUS = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.US);
        
        try {
            Date date0 = formatDefault0.parse("25/04/2014:20:06:54");// String parse to Date
            Date date1 = formatDefault1.parse("25/ËÄÔÂ/2014:20:06:54");
            Date date2 = formatUS.parse("25/Apr/2014:20:06:54");
            
            System.out.println(new SimpleDateFormat().format(date0));// Date format to String
            System.out.println(new SimpleDateFormat().format(date1));
            System.out.println(new SimpleDateFormat().format(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
