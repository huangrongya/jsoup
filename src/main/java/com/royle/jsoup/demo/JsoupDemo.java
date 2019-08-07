package com.royle.jsoup.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupDemo {

    public static void main(String[] args) {

        String detailDomain = "https://www.ylqxzb.com/zbPublish/";

        try {
            for (int i = 1; i <2; i++) {
                Document document = Jsoup.connect("https://www.ylqxzb.com/ZbPublish/Index_n"+i+".html").get();
                Elements trs = document.getElementsByTag("tr");
                for (Element tr : trs) {
                    Elements tds = tr.getElementsByTag("td");
                    if (tds.isEmpty()){continue;}
                    Element td1 = tds.get(1);
                    if (!td1.hasText()){continue;}
                    String time = tds.get(2).wholeText().trim();
                    String title = td1.wholeText().trim();
                    Element a = td1.getElementsByTag("a").first();
                    String link = a.attr("href");
                    System.out.println(title+" | "+detailDomain+link+" | "+time);
                    Document detail = Jsoup.connect(detailDomain+link).get();
                    Element span = detail.selectFirst("span#ctl00_ContentPlaceHolder1_InformationContent");
                    Elements contentP = span.children();
                    for (Element element : contentP) {
                        String content = element.html();
                        System.out.println(content);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
