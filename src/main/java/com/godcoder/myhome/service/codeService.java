package com.godcoder.myhome.service;

import com.godcoder.myhome.entity.codeDTO;
import com.godcoder.myhome.mapper.codeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class codeService {

    @Autowired
    codeMapper codemapper;

    public List<codeDTO> retrieveCode(String kndCd){

        List<codeDTO> rtnDto = codemapper.retrieveCode(kndCd);

        return rtnDto;
    }
}
