package mianshiTest;

/**
 * @Author: yanshuai
 * @Date: Created in 2018/3/9
 * @Description:    二分法描述
 * @Modified By:
 */
public class Test01 {
    public  int dichotomy( int arr[],int tag ){
        int first =0;
        int end = arr.length;
        for (int i=0;i<arr.length;i++ ){
            int middle = (first+end)/2;
            if (tag==arr[middle])
                return middle;
            if (tag>arr[middle])
                first=middle+1;
            if (tag<middle)
                end=middle-1;
        }
        return 0;
    }
}
