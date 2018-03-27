package com.fise.utils.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientUtil {

    private static Logger logger = Logger.getLogger(HttpClientUtil.class);  
    
    public static void main(String[] args) {
        //sendGetRequest("https://tieba.baidu.com/index.html");
        //sendPostRequest();
        urlRequest();
    }
    
    public static void sendGetRequest(String reqURL){
        String respContent = "通信失败"; //响应内容    
        HttpClient httpClient = new DefaultHttpClient(); //创建默认的httpClient实例    
        //设置代理服务器    
        //httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, new HttpHost("10.0.0.4", 8080));    
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000); //连接超时10s    
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);         //读取超时20s    
        HttpGet httpGet = new HttpGet(reqURL); //创建org.apache.http.client.methods.HttpGet    
        try{    
            HttpResponse response = httpClient.execute(httpGet); //执行GET请求    
            HttpEntity entity = response.getEntity();            //获取响应实体    
            if(null != entity){    
                //respCharset=EntityUtils.getContentCharSet(entity)也可以获取响应编码,但从4.1.3开始不建议使用这种方式    
                Charset respCharset = ContentType.getOrDefault(entity).getCharset();    
                respContent = EntityUtils.toString(entity, respCharset); 
                System.out.println(respContent);
                //Consume response content    
                EntityUtils.consume(entity);    
            }    
//            System.out.println("-------------------------------------------------------------------------------------------");    
            StringBuilder respHeaderDatas = new StringBuilder();    
            for(Header header : response.getAllHeaders()){    
                respHeaderDatas.append(header.toString()).append("\r\n");    
            }    
            //String respStatusLine = response.getStatusLine().toString(); //HTTP应答状态行信息    
            //String respHeaderMsg = respHeaderDatas.toString().trim();    //HTTP应答报文头信息    
            //String respBodyMsg = respContent;                            //HTTP应答报文体信息    
            //System.out.println("HTTP应答完整报文=[" + respStatusLine + "\r\n" + respHeaderMsg + "\r\n\r\n]");    
            //System.out.println("-------------------------------------------------------------------------------------------");    
        } catch (ConnectTimeoutException cte){    
            //Should catch ConnectTimeoutException, and don`t catch org.apache.http.conn.HttpHostConnectException    
            logger.error("请求通信[" + reqURL + "]时连接超时,堆栈轨迹如下", cte);    
        } catch (SocketTimeoutException ste){    
            logger.error("请求通信[" + reqURL + "]时读取超时,堆栈轨迹如下", ste);    
        }catch(ClientProtocolException cpe){    
            //该异常通常是协议错误导致:比如构造HttpGet对象时传入协议不对(将'http'写成'htp')or响应内容不符合HTTP协议要求等    
            logger.error("请求通信[" + reqURL + "]时协议异常,堆栈轨迹如下", cpe);    
        }catch(ParseException pe){    
            logger.error("请求通信[" + reqURL + "]时解析异常,堆栈轨迹如下", pe);    
        }catch(IOException ioe){    
            //该异常通常是网络原因引起的,如HTTP服务器未启动等    
            logger.error("请求通信[" + reqURL + "]时网络异常,堆栈轨迹如下", ioe);    
        }catch (Exception e){    
            logger.error("请求通信[" + reqURL + "]时偶遇异常,堆栈轨迹如下", e);    
        }finally{    
            //关闭连接,释放资源    
            httpClient.getConnectionManager().shutdown();    
        }    
        //System.out.println(respContent);    
    }

    
    public static void sendPostRequest(){
        try {
            URL url = new URL("http://blog.csdn.net/albertfly/article/details/51526322");

            URLConnection rulConnection = url.openConnection();// 此处的urlConnection对象实际上是根据URL的
            // 请求协议(此处是http)生成的URLConnection类
            // 的子类HttpURLConnection,故此处最好将其转化
            // 为HttpURLConnection类型的对象,以便用到
            // HttpURLConnection更多的API.如下:

            HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
            // http正文内，因此需要设为true, 默认情况下是false;
            httpUrlConnection.setDoOutput(true);

            // 设置是否从httpUrlConnection读入，默认情况下是true;
            httpUrlConnection.setDoInput(true);

            // Post 请求不能使用缓存
            httpUrlConnection.setUseCaches(false);

            // 设定传送的内容类型是可序列化的java对象
            // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
            httpUrlConnection.setRequestProperty("Content-type", "application/x-java-serialized-object");

            // 设定请求的方法为"POST"，默认是GET
            httpUrlConnection.setRequestMethod("POST");

            // 连接，从上述第2条中url.openConnection()至此的配置必须要在connect之前完成，
            httpUrlConnection.connect();
            
            String msg="";
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) { // 循环从流中读取
                msg += line + "\n";
            }
            reader.close(); // 关闭流 
            
            httpUrlConnection.disconnect();
            System.out.println(msg);
        } catch (IOException e) {           
            e.printStackTrace();
        }
    }
    
    public static void urlRequest(){
        try {
            URL url = new URL("http://blog.csdn.net/albertfly/article/details/51526322");
            InputStream in =url.openStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader bufr = new BufferedReader(isr);
            String str;
            while ((str = bufr.readLine()) != null) {
                System.out.println(str);
            }
            bufr.close();
            isr.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
