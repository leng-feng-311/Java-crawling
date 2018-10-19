package com.jason.springboot.application.service.impl;

import com.jason.springboot.application.DTO.PunishInfo;
import com.jason.springboot.application.DTO.SearchItem;
import com.jason.springboot.application.service.SearchItemService;
import com.jason.springboot.dao.SearchItemMapper;
import com.jason.springboot.repository.PunishRepository;
import com.jason.springboot.utils.SearchResult;
import com.jason.springboot.utils.TaotaoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SearchItemServiceImpl implements SearchItemService {

    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private PunishRepository punishRepository;

    @Override
    public TaotaoResult importItemToIndex() {
        List<SearchItem> chapterItems = searchItemMapper.getChapterItems();
        List<SearchItem> items = searchItemMapper.getItems();
        List<PunishInfo> punishInfos = new ArrayList<>();
        if (chapterItems != null && !chapterItems.isEmpty()) {
            chapterItems.forEach(item -> {
                PunishInfo punishInfo = new PunishInfo();
                punishInfo.setId(item.getId());
                punishInfo.setTitle(item.getTitle());
                punishInfo.setChapter(item.getChapter());
                punishInfo.setItems(item.getItems());
                punishInfo.setCategoryName(item.getCategoryName());
                punishInfos.add(punishInfo);
                punishRepository.save(punishInfo);
            });
        }
        if(items != null && !items.isEmpty()){
            items.forEach(item->{
                PunishInfo punishInfo = new PunishInfo();
                punishInfo.setId(item.getId());
                punishInfo.setTitle(item.getTitle());
                punishInfo.setChapter(item.getChapter());
                punishInfo.setItems(item.getItems());
                punishInfo.setCategoryName(item.getCategoryName());
                punishInfos.add(punishInfo);
                punishRepository.save(punishInfo);
            });
        }
        return TaotaoResult.ok(punishInfos);
    }

    @Override
    public TaotaoResult getItemByPro(String items, String categoryname, String sort, String pageNumber, String pageSize) {
        if (StringUtils.isEmpty(pageNumber) || StringUtils.isEmpty(pageSize)) {
            pageNumber = String.valueOf("1");
            pageSize = String.valueOf("60");
        }
        Sort sort1;
        if (StringUtils.isEmpty(sort)) {
            sort1 = new Sort(Sort.Direction.ASC, "id");
        } else {
            sort1 = new Sort(Sort.Direction.ASC, sort);
        }
        PageRequest pageRequest = new PageRequest(Integer.parseInt(pageNumber) - 1, Integer.parseInt(pageSize), sort1);
        Page<PunishInfo> query = punishRepository.findByItemsOrCategoryName(items, categoryname, pageRequest);
        SearchResult searchResult = new SearchResult();
        searchResult.setItemList(query.getContent());
        searchResult.setRecordCount(query.getTotalElements());
        searchResult.setTotalPages(query.getTotalPages());
        return new TaotaoResult(searchResult);
    }

}
