package cn.edu.tju.scs.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import javax.print.DocFlavor;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Takahashi on 2016/12/1.
 */
public class SolrUtil {

    public static String getSolrResultsJson(String search){
        String urlNameString = search;
        System.out.println(urlNameString);
//        String urlNameString = "https://api.weixin.qq.com/sns/userinfo?access_token=TOKEN&openid=OPENID";
//        urlNameString=urlNameString.replace("TOKEN", token);
//        urlNameString=urlNameString.replace("OPENID",openid);
        String result="";

        try{
            // 根据地址获取请求
            HttpGet request = new HttpGet(urlNameString);//这里发送get请求
//            request.setParams();
            System.out.println(urlNameString);
            // 获取当前客户端对象
            HttpClient httpClient = new DefaultHttpClient();

            // 通过请求对象获取响应对象
            HttpResponse response = httpClient.execute(request);

            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result= EntityUtils.toString(response.getEntity());
                System.out.print(result);
            }

        }catch (Exception e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String args[]){
//        String url = "http://localhost:8983/solr/music_siteCore/select?fl=id,name,visit,info&indent=on&q=location:*云南*&wt=json";
//
//        String a = "http://localhost:8983/solr/music_siteCore/select?fl=id,name,visit,info&indent=on&q=*:* AND category:*丝*&wt=json";
//
//        System.out.println();
//        System.out.println();
//
//        String res = getSolrResultsJson(a);
//        int start = res.indexOf("[");
//        int end = res.lastIndexOf("]") + 1;
//        res = res.substring(start,end);
//        System.out.println(res);

//        BrefInfo brefInfo1 = new BrefInfo(1, "name1", "info1", 100);
//        BrefInfo brefInfo2 = new BrefInfo(2, "name2", "info2", 200);
//
//        List<BrefInfo> brefInfoList = new ArrayList<BrefInfo>();
//        brefInfoList.add(brefInfo1);
//        brefInfoList.add(brefInfo2);
//
//        System.out.println(JsonSerializeUtil.objectToJson(brefInfoList));

//        List<BrefInfo> brefInfoList1 = JsonSerializeUtil.jsonToObject(res);


//        String a = "%E9%87%91";
//        String b = "%C3%A9%C2%87%C2%91";
//        try{
//            System.out.println(URLEncoder.encode("金", "UTF-8"));
//            System.out.println(URLDecoder.decode(a,"UTF-8"));
//            System.out.println(URLDecoder.decode(b, "UTF-8"));
//        }catch (Exception e)
//        {
//            System.out.println("xxx");
//        }

        System.out.println("");

        return;
    }
}
