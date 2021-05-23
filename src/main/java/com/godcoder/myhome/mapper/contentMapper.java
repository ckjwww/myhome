package com.godcoder.myhome.mapper;

import com.godcoder.myhome.entity.contentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface contentMapper {

    contentDTO retrieveContent();

}
