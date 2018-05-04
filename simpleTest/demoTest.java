package simpleTest;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: yanshuai
 * @Date: Created in 2018/3/29
 * @Description:
 * @Modified By:
 */
public class demoTest {
    @Test
    public void test(){
//        int page=24;
//        if (page/500!=0&&page%500==0) System.out.println("page in "+((page/500-1)*500+1)+"_"+(page/500)*500);
//        else System.out.println("page in "+((page/500)*500+1)+"_"+(page/500+1)*500);
//        if (page/500!=0&&page%500!=0){}
//        else System.out.println("page in "+(page/500-1)*500+"-"+(page/500)*500);

        int page = 16;
        String ggdm = "TMWXGG";
        int pdvl = 1542;
        String imgUrl;
        int shang = page/500;  //商
        int yu = page%500;     //余
        String pageRange;
        if (shang!=0&&yu==0) pageRange=((shang-1)*500+1)+"_"+shang*500;
        else pageRange=(shang*500+1)+"_"+(shang+1)*500;
        //http://gonggaoimg.ipdtfc.com/1000/TMSDGG/1_500/1000_TMSDGG_100.jpg
       imgUrl="http://gonggaoimg.ipdtfc.com/"+pdvl+"/"+ggdm+"/"+pageRange+"/"+pdvl+"_"+ggdm+"_"+page+".jpg";
       System.out.println(imgUrl);
    }
    @Test
    public void testNumber(){
        String str = "张三丰22095619900101121139";
        String str2 = "张三丰SDACAS22095619900101120X";
//        Pattern p = Pattern.compile("\\d{18}");
//        Pattern p = Pattern.compile("[0-9]{14,17}[A-Z]$");
        //筛选身份证号的正则表达式
        Pattern p = Pattern.compile("\\d{18}|\\d{17}[X|x]|\\d{15}");
        Matcher m = p.matcher(str2);
        String result=m.find()?m.group():null;
        String target = "";
        if (result!=null) target=result.replace(result.substring(6,14),"********");
        String ap = str2.replace(result, target);
        System.out.println(ap);
//        System.out.println(str.replace(result,"220956********1239"));
//        str.indexOf(result);
//        System.out.println(str.indexOf(result));
//        System.out.println(str);
//        System.out.println(result);
//        System.out.println(str);
    }

}
