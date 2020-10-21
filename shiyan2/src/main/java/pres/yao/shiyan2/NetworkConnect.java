package pres.yao.shiyan2;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ClassName NetworkConnect
 * @Description TOOD
 * Date 2020/10/14 15:41
 **/
public class NetworkConnect {
    public final static String iCiBaURL1="http://dict-co.iciba.com/api/dictionary.php?w=";
    public final static String iCiBaURL2="&key=BDC37F4ACF9FB5F102A8B1EFA3C0263E";
    public static InputStream getInputStreamByUrl(String urlStr){
        InputStream tempInput=null;
        //1.实例化一个URL对象
        URL url=null;
        //2.获取HttpURLConnection实例
        HttpURLConnection connection=null;
        //设置超时时间

        try{
            url=new URL(urlStr);
            connection=(HttpURLConnection)url.openConnection();     //别忘了强制类型转换
            //3.设置和请求相关属性
            //请求方式
            //connection.setRequestMethod("GET");
            // 请求超时时长
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(10000);
            tempInput=connection.getInputStream();
           /* //4.获取响应码
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK) {
                //5.判断响应码并获取响应数据
                tempInput=connection.getInputStream();
                //在循环中读取输入流
                byte[] b = new byte[1024];
                int len = 0;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while ((len = tempInput.read(b)) > -1){
                    //参数1:待写入的数组
                    //参数2:起点
                    //参数3:长度,最后一次为实际长度
                    byteArrayOutputStream.write(b,0,len);
                }
                String msg = new String(byteArrayOutputStream.toString());
                Log.e("TAG",msg+"===========");
            }*/
            //Log.e("TAG",tempInput.toString()+"=========");

        }catch(Exception e){
            e.printStackTrace();
        }
        return tempInput;
    }
}
