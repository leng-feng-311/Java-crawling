package com.jason.springboot.controller;

import com.jason.springboot.application.service.SearchItemService;
import com.jason.springboot.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolrController {
    @Autowired
    private SearchItemService searchItemService;

    @GetMapping("/solr")
    public TaotaoResult importItem() {
        return searchItemService.importItemToIndex();
    }

    @GetMapping("/querySolr")
    public TaotaoResult getItemByPro(String items,String categoryname,String sort,String pageNumber,String pageSize) {
        return searchItemService.getItemByPro(items,categoryname,sort,pageNumber,pageSize);
    }
}
