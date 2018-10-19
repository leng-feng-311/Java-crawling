package com.jason.springboot.solr;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestSolrJ {
    @Test
    public void testAddDocument() throws Exception {
        //创建一个SolrServer对象。创建一个HttpSolrServer对象
        //需要指定solr服务的url
//        HttpSolrClient
//        SolrServer solrServer = new HttpSolrServer("http://192.168.25.154:8080/solr/collection1");
//        //创建一个文档对象SolrInputDocument
//        SolrInputDocument document = new SolrInputDocument();
//        //向文档中添加域，必须有id域，域的名称必须在schema.xml中定义
//        document.addField("id", "123");
//        document.addField("item_title", "测试商品3");
//        document.addField("item_price", 1000);
//        //把文档对象写入索引库
//        solrServer.add(document);
//        //提交
//        solrServer.commit();
    }
}
