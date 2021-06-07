package com.godcoder.myhome.mapper;

import com.godcoder.myhome.entity.baseballDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface baseballMapper {

    baseballDTO retrieveBaseball();

    List<baseballDTO> retrieveBaseballist();

    int insertBaseball(baseballDTO baseballDto);

    int updateBaseball(baseballDTO baseballDto);

    int deleteBaseball(String manageId);
}
