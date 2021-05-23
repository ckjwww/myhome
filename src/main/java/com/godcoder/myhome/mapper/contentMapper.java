package com.godcoder.myhome.mapper;

import com.godcoder.myhome.entity.contentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface contentMapper {

    List<contentDTO> retrieveContents();

    contentDTO retrieveContent(int id);

    int insertContent(contentDTO contentdto);
}
