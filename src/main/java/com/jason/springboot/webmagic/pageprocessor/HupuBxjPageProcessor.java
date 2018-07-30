package com.jason.springboot.webmagic.pageprocessor;

import com.jason.springboot.application.DTO.PunishDTO;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.imageio.plugins.tiff.TIFFDirectory;
import java.util.List;
import java.util.Random;

/**
 * Created by jiazefeng on 18/07/26.
 */
public class HupuBxjPageProcessor implements PageProcessor {//修改改类，定制自己的抽取逻辑

    //抓取网站的相关配置，包括编码、抓取间隔、重试次数、代理、UserAgent等
    private Site site = Site.me()
//            .addHeader("Proxy-Authorization", ProxyGeneratedUtil.authHeader(ORDER_NUM, SECRET, (int) (new Date().getTime()/1000)))//设置代理
//            .setDisableCookieManagement(true)
//            .setCharset("GB2312")
            .setCharset("UTF-8")
            .setTimeOut(30000)
            .setRetryTimes(3)
            .setSleepTime(new Random().nextInt(20) * 100);
//            .setUserAgent(UserAgentUtil.getRandomUserAgent());

    @Override
    public void process(Page page) {
//        crawlPost(page);
//        aslPost(page);
//        aslirPost(page);
        cpcPost(page);
    }


    @Override
    public Site getSite() {
        return site;
    }

    /**
     * 抓取<<中国共产党纪律处分条例>>信息
     *
     * @param page 当前页面对象
     */
    private void crawlPost(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(http://cpc\\.people\\.com\\.cn/\\w+/\\w+)").all());
        List<String> titleList = page.getHtml().xpath("//font[@class='fbody']/b/center/text()").all();
        List<String> chapterList = page.getHtml().xpath("//font[@class='fbody']/b/text()").all();
        String item = page.getHtml().xpath("//font[@class='fbody']/text()").toString();
        setPunish(titleList, chapterList, item, page);
        //评论内容
//        List<String> contentList = page.getHtml().xpath("//div[@class='w_reply clearfix']//td/text()").all();
        //评论点亮数
//        List<String> litNumList = page.getHtml().xpath("//div[@class='w_reply clearfix']//span[@class='ilike_icon_list']/span[@class='stime']/text()").all();
        //评论作者
//        List<String> commentAuthors = page.getHtml().xpath("//div[@class='w_reply clearfix']//div[@class='author']//a[@class='u']/text()").all();
    }

    /**
     * 获取中国共产党纪律处分条例
     *
     * @param titleList
     * @param chapterList
     * @param item
     * @param page
     */
    private void setPunish(List<String> titleList, List<String> chapterList, String item, Page page) {
        PunishDTO punishDTO = new PunishDTO();
        punishDTO.setTitleList(titleList);
        punishDTO.setChapterList(chapterList);
        punishDTO.setItem(item);
        page.putField("punishInfo", punishDTO);
    }

    /**
     * 抓取中华人民共和国行政监察法信息
     * Administrative supervision law
     *
     * @param page
     */
    private void aslPost(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(http://www\\.gov\\.cn/\\w+/\\w+)").all());
        List<String> titleList = page.getHtml().xpath("//font[@id=\"Zoom\"]/p/strong/text()").all();
        List<String> chapterList = page.getHtml().xpath("//font[@id=\"Zoom\"]/p/text()").all();
        setAsl(titleList, chapterList, page);
    }

    /**
     * 返回中华人民共和国行政监察法信息
     *
     * @param titleList
     * @param chapterList
     * @param page
     */
    private void setAsl(List<String> titleList, List<String> chapterList, Page page) {
        PunishDTO punishDTO = new PunishDTO();
        punishDTO.setTitleList(titleList);
        punishDTO.setChapterList(chapterList);
        page.putField("aslInfo", punishDTO);
    }

    /**
     * 抓取 中华人民共和国行政监察法实施条例 信息
     * Administrative Supervision Law Implementation Regulations
     *
     * @param page
     */
    private void aslirPost(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(http://jjc\\.cq\\.gov\\.cn/\\w+/\\w+)").all());
        List<String> chapterList = page.getHtml().xpath("//div[@class=\"nr\"]/p/text()").all();
        setAslir(chapterList, page);
    }

    private void setAslir(List<String> chapterList, Page page) {
        PunishDTO punishDTO = new PunishDTO();
        punishDTO.setChapterList(chapterList);
        page.putField("aslirInfo", punishDTO);
    }

    /**
     * 抓取 中国共产党章程 信息
     * Communist Party Constitution
     *
     * @param page
     */
    private void cpcPost(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(http://www\\.12371\\.cn/\\w+/\\w+)").all());
        List<String> titleList = page.getHtml().xpath("//div[@class='word']/p/strong/text()").all();
        List<String> chapterList = page.getHtml().xpath("//div[@class='word']/p/text()").all();
        setCPC(titleList, chapterList, page);
    }

    private void setCPC(List<String> titleList, List<String> chapterList, Page page) {
        PunishDTO punishDTO = new PunishDTO();
        punishDTO.setTitleList(titleList);
        punishDTO.setChapterList(chapterList);
        page.putField("cpcInfo", punishDTO);
    }

    /**
     * 行政机关公务员处分条例
     * Administrative Organs Disciplinary Regulations
     * @param page
     */
    private void aodrPost(Page page){
        page.addTargetRequests(page.getHtml().links().regex("(http://jxw\\.panzhihua\\.gov\\.cn/\\w+/\\w+)").all());
        List<String> titleList = page.getHtml().xpath("//div[@class='nr3']/p/strong/text()").all();
        List<String> chapterList = page.getHtml().xpath("//div[@class='word']/p/text()").all();
    }
}
