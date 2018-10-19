package com.jason.springboot.dao;

import com.jason.springboot.application.DTO.SearchItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SearchItemMapper {
    List<SearchItem> getItemList(@Param("level") int level, @Param("parentId") int parentId);

    List<SearchItem> getItems();

    List<SearchItem> getChapterItems();
}
