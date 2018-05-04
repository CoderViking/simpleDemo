package jsonToMapTest;

/**
 * @Author: viking
 * @Date: Created in 2018/4/10
 * @Description:
 * @Modified By:
 */


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
//import net.sf.json.JSONObject;

public class Test01 {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    //配置您申请的KEY
    public static final String APPKEY ="your appkey";

    //1.站到站查询（含票价）
    public static void getRequest1(){
        String result =null;
        String url ="http://apis.juhe.cn/train/s2swithprice";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("start","");//出发站
        params.put("end","");//终点站
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.parseObject(result);
            if(Integer.parseInt(object.get("error_code").toString())==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2.12306订票②：车次票价查询
    public static void getRequest2(){
        String result =null;
        String url ="http://apis.juhe.cn/train/ticket.price.php";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("train_no","");//列次编号，对应12306订票①：查询车次中返回的train_no
        params.put("from_station_no","");//出发站序号，对应12306订票①：查询车次中返回的from_station_no
        params.put("to_station_no","");//出发站序号，对应12306订票①：查询车次中返回的to_station_no
        params.put("date","");//默认当天，格式：2014-12-25
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.parseObject(result);
            if(Integer.parseInt(object.get("error_code").toString())==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3.车次查询
    public static void getRequest3(String trainId){
        String result =null;
        String url ="http://apis.juhe.cn/train/s";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("name",trainId);//车次名称，如：G4
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.parseObject(result);
            if(Integer.parseInt(object.get("error_code").toString())==0){
                System.out.println(object.get("result"));
                Map<String,Object> map = (Map<String, Object>) object.get("result");
                System.out.println("INfo:"+map.get("train_info"));
                Map<String,String> info = (Map<String,String>) map.get("train_info");
                System.out.println("车次名:"+info.get("name"));
                System.out.println("发车时间:"+info.get("starttime"));
                System.out.println("出发站:"+info.get("start"));
                System.out.println("终点站:"+info.get("end"));
                System.out.println("到站时间:"+info.get("endtime"));
                System.out.println(map);
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4.站到站查询
    public static void getRequest4(){
        String result =null;
        String url ="http://apis.juhe.cn/train/s2s";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("start","");//出发站
        params.put("end","");//终点站
        params.put("traintype","");//列车类型，G-高速动车 K-快速 T-空调特快 D-动车组 Z-直达特快 Q-其他
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.parseObject(result);
            if(Integer.parseInt(object.get("error_code").toString())==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //5.12306实时余票查询
    public static void getRequest5(){
        String result =null;
        String url ="http://apis.juhe.cn/train/yp";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json
        params.put("from","");//出发站,如：上海虹桥
        params.put("to","");// 到达站,如：温州南
        params.put("date","");//出发日期，默认今日
        params.put("tt","");//车次类型，默认全部，如：G(高铁)、D(动车)、T(特快)、Z(直达)、K(快速)、Q(其他)

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.parseObject(result);
            if(Integer.parseInt(object.get("error_code").toString())==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //6.12306订票①：查询车次
    public static void getRequest6(){
        String result =null;
        String url ="http://apis.juhe.cn/train/ticket.cc.php";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("from","");//出发站名称：如上海虹桥
        params.put("to","");//到达站名称：如温州南
        params.put("date","");//默认当天，格式：2014-07-11
        params.put("tt","");//车次类型，默认全部，如：G(高铁)、D(动车)、T(特快)、Z(直达)、K(快速)、Q(其他)
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.parseObject(result);
            if(Integer.parseInt(object.get("error_code").toString())==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //7.火车票代售点查询
    public static void getRequest7(){
        String result =null;
        String url ="http://apis.juhe.cn/train/dsd";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("province","");//省份,如：浙江
        params.put("city","");//城市，如：温州
        params.put("county","");//区/县，如：鹿城区
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.parseObject(result);
            if(Integer.parseInt(object.get("error_code").toString())==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //8.列车站点列表
    public static void getRequest8(){
        String result =null;
        String url ="http://apis.juhe.cn/train/station.list.php";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.parseObject(result);
            if(Integer.parseInt(object.get("error_code").toString())==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        getRequest3("K1270");
    }

    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    //将map型转为请求参数型
    public static String urlencode(Map<String,String> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
