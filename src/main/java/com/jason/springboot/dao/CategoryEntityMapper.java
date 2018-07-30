package com.jason.springboot.dao;

import com.jason.springboot.model.CategoryEntity;

public interface CategoryEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CategoryEntity record);

    int insertSelective(CategoryEntity record);

    CategoryEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CategoryEntity record);

    int updateByPrimaryKey(CategoryEntity record);
}