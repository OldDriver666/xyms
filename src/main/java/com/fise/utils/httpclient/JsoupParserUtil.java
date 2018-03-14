package com.fise.utils.httpclient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.fise.model.entity.Search;

public class JsoupParserUtil {

    public static List<Search> getElement(String param,Integer page) throws IOException {
        //File file = new File("D:/CSDN.NET.html");
        
        String reqURL="https://m.baidu.com/s?word="+param+"&pn="+(page-1)*10;
        List<Search> list = new ArrayList<Search>();
        
        String str标题 = "";
        String strURL = "";
        String str图片 = "";
        //String str阅读次数 = "";
        String str内容 = "";
        //String str评论数 = "";
        Document doc = Jsoup.connect(reqURL).get();
        Document docSub;// 博客每一项
        Elements elmPerLink;// 列表中的每一个博客超链接
        // ---------------------------------------------
        Elements content = doc.getElementsByClass("c-container");
        for (int i = 0; i < content.size(); i++) {
            docSub = Jsoup.parse(content.get(i).toString());
            // 标题+链接
            elmPerLink = content.get(i).getElementsByTag("a");
            //str标题 = elmPerLink.get(0).text();
            strURL = elmPerLink.get(0).attr("href");
            str标题 = docSub.getElementsByClass("c-title c-gap-top-small").text();
            
            str图片 = content.get(i).getElementsByTag("img").attr("src").toString();
            
            
            //str阅读次数 = getNum(str阅读次数_全);

            str内容 = content.get(i).getElementsByTag("p").text();
            //str评论数 = getNum(str评论数_全);

           /* System.out.println("标题: " + str标题);
            System.out.println("URL: " + strURL);
            System.out.println("阅读图片: " + str图片);
            System.out.println("阅读内容: " + str内容);*/
            
            Search search = new Search(str标题, strURL, str图片, str内容);
            list.add(search);
        }
        
        return list;
    }

}
