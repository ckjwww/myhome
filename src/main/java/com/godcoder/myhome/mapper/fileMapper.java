package com.godcoder.myhome.mapper;

import com.godcoder.myhome.entity.fileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface fileMapper {

    fileDTO retrieveFile(String fileId);

    int insertFile(fileDTO fileDto);
}
