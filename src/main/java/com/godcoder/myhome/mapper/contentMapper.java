package com.godcoder.myhome.mapper;

import com.godcoder.myhome.entity.contentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface contentMapper {

    List<contentDTO> retrieveContents(Map map);

    contentDTO retrieveContent(int id);

    int insertContent(contentDTO contentdto);

    int deleteContent(int id);

    int getContentCnt(Map map);
}
