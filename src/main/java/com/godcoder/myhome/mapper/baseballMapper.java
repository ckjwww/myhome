package com.godcoder.myhome.mapper;

import com.godcoder.myhome.entity.baseballDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface baseballMapper {

    baseballDTO retrieveBaseball();

    int insertBaseball(baseballDTO baseballDto);

    int updateBaseball(baseballDTO baseballDto);
}
