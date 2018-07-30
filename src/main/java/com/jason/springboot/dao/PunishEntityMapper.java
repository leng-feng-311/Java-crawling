package com.jason.springboot.dao;

import com.jason.springboot.model.PunishEntity;
import com.jason.springboot.model.PunishEntityWithBLOBs;

public interface PunishEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PunishEntityWithBLOBs record);

    int insertSelective(PunishEntityWithBLOBs record);

    PunishEntityWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PunishEntityWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PunishEntityWithBLOBs record);

    int updateByPrimaryKey(PunishEntity record);
}