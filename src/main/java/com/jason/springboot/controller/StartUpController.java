package com.jason.springboot.controller;

import com.jason.springboot.webmagic.downloader.CrowProxyProvider;
import com.jason.springboot.webmagic.pageprocessor.HupuBxjPageProcessor;
import com.jason.springboot.webmagic.pipeline.HupuSpiderPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;

/**
 * Created by jiazefeng on 18/07/26.
 */
@RestController
public class StartUpController {

    @Autowired
    HupuSpiderPipeline hupuSpiderPipeline;

    /*
    @Autowired
    ProxyIpMapper proxyIpMapper;
    */
    @GetMapping("/")
    public String index() {

        Spider.create(new HupuBxjPageProcessor())
                //中国共产党纪律处分条例
                // .setCharset("GB2312")
//                .addUrl("http://cpc.people.com.cn/GB/64162/71380/71382/71384/4856229.html")
                //中华人民共和国行政监察法
//                .addUrl("http://www.gov.cn/jrzg/2010-06/26/content_1637915.htm")
                //中华人民共和国行政监察法实施条例
//                .addUrl("http://jjc.cq.gov.cn/html/2014-11/06/content_31989305.htm")
                //中国共产党章程
//                .addUrl("http://www.12371.cn/2017/10/28/ARTI1509191507150883.shtml")
                //行政机关公务员处分条例
//                .addUrl("http://jxw.panzhihua.gov.cn/xxgk/zdxxgk/lzjy/698611.shtml")
                //中国共产党纪律检查机关案件检查工作条例
                // .setCharset("GB2312")
//                .addUrl("http://cpc.people.com.cn/GB/64162/71380/71387/71590/4855987.html")
                //中国共产党纪律检查机关案件检查工作条例实施细则
                // .setCharset("GB2312")
//                .addUrl("http://cpc.people.com.cn/GB/64162/71380/71387/71590/4855997.html")
                //中国共产党纪律检查机关控告申诉工作条例
                // .setCharset("GB2312")
//                .addUrl("http://cpc.people.com.cn/GB/64162/71380/71387/71590/4855976.html")
                //监察机关举报工作办法
                // .setCharset("UTF-8")
//                .addUrl("http://www.gov.cn/fwxx/content_2266820.htm")
                //中华人民共和国信访条例
                // .setCharset("GB2312")
//                .addUrl("http://www.gjxfj.gov.cn/2005-01/18/content_3583093.htm")
                //中国共产党廉洁自律准则
                //.setCharset("UTF-8")
//                .addUrl("http://news.12371.cn/2015/10/22/ARTI1445481444215144.shtml")
                //中国共产党党内监督条例
                //.setCharset("UTF-8")
//                .addUrl("http://news.12371.cn/2016/11/02/ARTI1478087905680175.shtml")
                //事业单位人事管理条例
                //.setCharset("UTF-8")
                .addUrl("http://news.12371.cn/2014/05/15/ARTI1400120337721176.shtml")
                .addPipeline(hupuSpiderPipeline)
                .thread(4)
                .run();
        return "爬虫开启";
    }
}
