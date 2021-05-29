package com.godcoder.myhome.mapper;

import com.godcoder.myhome.entity.baseballDTO;
import com.godcoder.myhome.entity.codeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface codeMapper {

    List<codeDTO> retrieveCode(String kndCd);
}
