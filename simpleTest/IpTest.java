package simpleTest;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by yanshuai on 2018/5/2
 */
public class IpTest {

    @Test
    public void test001() throws Exception{
        InetAddress address = InetAddress.getLocalHost();

        System.out.println(address.toString());
        byte[] address1 = address.getAddress();
        for (byte add:address1){
            System.out.println(add);
        }
        String ip = address.getHostAddress().toString();
        System.out.println(ip);
        System.out.println("cmd:"+getLocalIPForCMD());
        System.out.println("java:"+getLocalIPForJava());
    }
    public static String getLocalIPForCMD(){
        StringBuilder sb = new StringBuilder();
        String command = "cmd.exe /c ipconfig | findstr IPv4";
        try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line ;
            while((line = br.readLine()) != null){
                line = line.substring(line.lastIndexOf(":")+2,line.length());
                sb.append(line+";");
            }
            br.close();
            p.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    @Test
    public void  getTestForCMD(){
        String command = "cmd.exe /c";
        try {
            Process p = Runtime.getRuntime().exec(command);
            p.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLocalIPForJava(){
        StringBuilder sb = new StringBuilder();
        String ip = "";
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = en.nextElement();
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()  && !inetAddress.isLinkLocalAddress()
                            && inetAddress.isSiteLocalAddress()) {
                        sb.append(inetAddress.getHostAddress().toString()+"\n");
                        ip+=inetAddress.getHostAddress().toString()+";";
                    }
                }
            }
        } catch (SocketException e) {  }
        System.out.println("ip:"+ip);
        String IP = ip.split(";")[0];
        System.out.println("IP:"+IP);
        return sb.toString();
    }
}
