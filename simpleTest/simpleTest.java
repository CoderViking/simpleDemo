package simpleTest;

import org.junit.Test;

/**
 * @Author: yanshuai
 * @Date: Created in 2018/4/18
 * @Description:
 * @Modified By:
 */
public class simpleTest {
    @Test
    public void test(){
        String cid = "CN123456786";
        String str = cid.substring(0,2);
        System.out.println(str);
    }
}
