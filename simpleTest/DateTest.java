package simpleTest;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

    @Test
    public void test(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR));
//        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+1);
        Date newDate = calendar.getTime();
        String format = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
        System.out.println(calendar.toString());
        calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)-1);
        Date oneYear = calendar.getTime();
        String format2 = sdf.format(calendar.getTime());
        System.out.println("oneYear:"+sdf.format(calendar.getTime()));
        calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)-3);
        Date threeYear = calendar.getTime();
        System.out.println("threeYear:"+sdf.format(calendar.getTime()));
        if (threeYear.before(oneYear)) System.out.println("测试成功~");
        System.out.println(format);
    }
}
