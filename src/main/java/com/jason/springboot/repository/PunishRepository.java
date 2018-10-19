package com.jason.springboot.repository;

import com.jason.springboot.application.DTO.PunishInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Boost;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface PunishRepository extends SolrCrudRepository<PunishInfo, String> {

//    @Query(value = "item_title:*?0* or item_chapter:*?0* or item_items:*?0* or item_category_name:*?0*")
    Page<PunishInfo> findByItemsOrCategoryName(@Boost(2) String items, String categoryName, Pageable pageable);
}
