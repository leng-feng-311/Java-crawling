package com.jason.springboot.application.service;

import com.jason.springboot.utils.TaotaoResult;

public interface SearchItemService {
    TaotaoResult importItemToIndex();


    TaotaoResult getItemByPro(String items, String categoryname, String sort, String pageNumber, String pageSize);
}
