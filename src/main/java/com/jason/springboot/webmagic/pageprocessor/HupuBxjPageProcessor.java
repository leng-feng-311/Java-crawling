package com.jason.springboot.webmagic.pageprocessor;

import com.jason.springboot.application.DTO.PunishDTO;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

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
//        cpcPost(page);
//        aodrPost(page);
//        dioPost(page);
//        rirPost(page);
//        diacPost(page);
//        ormPost(page);
//        lvoPost(page);
//        selfPost(page);
//        superPost(page);
        ipmrPost(page);
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
     *
     * @param page
     */
    private void aodrPost(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(http://jxw\\.panzhihua\\.gov\\.cn/\\w+/\\w+)").all());
        List<String> titleList = page.getHtml().xpath("//div[@class='nr3']/p/strong/span/text()").all();
        List<String> chapterList = page.getHtml().xpath("//div[@class='nr3']/p/span[@style='font-family: 仿宋_GB2312; font-size: 14pt']/text()").all();
        setAodrPost(titleList, chapterList, page);
    }

    private void setAodrPost(List<String> titleList, List<String> chapterList, Page page) {
        PunishDTO punishDTO = new PunishDTO();
        punishDTO.setTitleList(titleList);
        punishDTO.setChapterList(chapterList);
        page.putField("aodrInfo", punishDTO);
    }

    /**
     * 中国共产党纪律检查机关案件检查工作条例
     * Regulations on the Inspection of Cases of the Communist Party of China Discipline Inspection Organs
     *
     * @param page
     */
    private void dioPost(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(http://cpc\\.people\\.com\\.cn/\\w+/\\w+)").all());
        List<String> titleList = page.getHtml().xpath("//font[@class='fbody']/b/text()").all();
        String items = page.getHtml().xpath("//font[@class='fbody']/text()").toString();
        setDio(titleList, items, page);
    }

    private void setDio(List<String> titleList, String items, Page page) {
        PunishDTO punishDTO = new PunishDTO();
        punishDTO.setTitleList(titleList);
        punishDTO.setItem(items);
        page.putField("dioInfo", punishDTO);
    }

    /**
     * 中国共产党纪律检查机关案件检查工作条例实施细则
     * Rules for the Implementation of the Regulations on the Inspection of Cases by the Communist Party of China
     *
     * @param page
     */
    private void rirPost(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(http://cpc\\.people\\.com\\.cn/\\w+/\\w+)").all());
        List<String> titleList = page.getHtml().xpath("//font[@class='fbody']/b/text()").all();
        String items = page.getHtml().xpath("//font[@class='fbody']/text()").toString();
        setRir(titleList, items, page);
    }

    private void setRir(List<String> titleList, String items, Page page) {
        PunishDTO punishDTO = new PunishDTO();
        punishDTO.setTitleList(titleList);
        punishDTO.setItem(items);
        page.putField("rirInfo", punishDTO);
    }

    /**
     * 中国共产党纪律检查机关控告申诉工作条例
     * The Communist Party of China's disciplinary inspection agency accuses the complaints work regulations
     *
     * @param page
     */
    private void diacPost(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(http://cpc\\.people\\.com\\.cn/GB/64162/71380/71387/71590/\\w+/\\w+)").all());
        List<String> titleList = page.getHtml().xpath("//font[@class='fbody']/b/text()").all();
        String items = page.getHtml().xpath("//font[@class='fbody']/text()").toString();
        setDiac(titleList, items, page);
    }

    private void setDiac(List<String> titleList, String items, Page page) {
        PunishDTO punishDTO = new PunishDTO();
        punishDTO.setTitleList(titleList);
        punishDTO.setItem(items);
        page.putField("diacInfo", punishDTO);
    }

    /**
     * 监察机关举报工作办法
     * Ombudsman reporting methods
     *
     * @param page
     */
    private void ormPost(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(http://www\\.gov\\.cn/fwxx/\\w+/\\w+)").all());
        List<String> titleList = page.getHtml().xpath("//font[@id='Zoom']/p/text()").all();
        setOrm(titleList, page);
    }

    private void setOrm(List<String> list, Page page) {
        PunishDTO punishDTO = new PunishDTO();
        punishDTO.setTitleList(list);
        page.putField("ormInfo", punishDTO);
    }

    /**
     * 中华人民共和国信访条例
     * Letters and Visits Ordinance
     */
    private void lvoPost(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(http://www\\.gjxfj\\.gov\\.cn/2005-01/18/\\w+/\\w+)").all());
        List<String> titleList = page.getHtml().xpath("//font[@id='Zoom']//p[@align='center']/strong/text()").all();
        List<String> itemList = page.getHtml().xpath("//font[@id='Zoom']//p/text()").all();
        setLvo(titleList, itemList, page);
    }

    private void setLvo(List<String> titleList, List<String> itmeList, Page page) {
        PunishDTO punishDTO = new PunishDTO();
        punishDTO.setTitleList(titleList);
        punishDTO.setChapterList(itmeList);
        page.putField("lvoInfo", punishDTO);
    }

    /**
     * 中国共产党廉洁自律准则
     * Chinese Communist Party's self-discipline guidelines
     */
    private void selfPost(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(http://news\\.12371\\.cn/2015/10/22/\\w+/\\w+)").all());
        List<String> titleList = page.getHtml().xpath("//div[@class='word']/p/strong/text()").all();
        List<String> itemList = page.getHtml().xpath("//div[@class='word']/p/text()").all();
        setSelf(titleList, itemList, page);
    }

    private void setSelf(List<String> titleList, List<String> itmeList, Page page) {
        PunishDTO punishDTO = new PunishDTO();
        punishDTO.setTitleList(titleList);
        punishDTO.setChapterList(itmeList);
        page.putField("selfInfo", punishDTO);
    }

    /**
     * 中国共产党党内监督条例
     * Communist Party of China Party Supervision Regulations
     */
    private void superPost(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(http://news\\.12371\\.cn/2016/11/02/\\w+/\\w+)").all());
        List<String> titleList = page.getHtml().xpath("//div[@class='word']/p/strong/text()").all();
        List<String> itemList = page.getHtml().xpath("//div[@class='word']/p/text()").all();
        setSuper(titleList, itemList, page);
    }

    private void setSuper(List<String> titleList, List<String> itmeList, Page page) {
        PunishDTO punishDTO = new PunishDTO();
        punishDTO.setTitleList(titleList);
        punishDTO.setChapterList(itmeList);
        page.putField("superInfo", punishDTO);
    }

    /**
     * 事业单位人事管理条例
     * Institutional Personnel Management Regulations
     */
    private void ipmrPost(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(http://news\\.12371\\.cn/2014/05/15/\\w+/\\w+)").all());
        List<String> titleList = page.getHtml().xpath("//div[@class='word']/p/strong/text()").all();
        List<String> itemList = page.getHtml().xpath("//div[@class='word']/p/text()").all();
        setIPMR(titleList, itemList, page);
    }

    private void setIPMR(List<String> titleList, List<String> itmeList, Page page) {
        PunishDTO punishDTO = new PunishDTO();
        punishDTO.setTitleList(titleList);
        punishDTO.setChapterList(itmeList);
        page.putField("ipmrInfo", punishDTO);
    }
}
