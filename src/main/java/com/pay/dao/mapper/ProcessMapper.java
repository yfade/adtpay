package com.pay.dao.mapper;

import com.pay.dao.model.Process;

public interface ProcessMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Process record);

    int insertSelective(Process record);

    Process selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Process record);

    int updateByPrimaryKey(Process record);
}