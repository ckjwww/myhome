package com.godcoder.myhome.service;

import com.godcoder.myhome.entity.baseballDTO;
import com.godcoder.myhome.mapper.baseballMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class baseballService {

    @Autowired
    baseballMapper baseballmapper;

    public baseballDTO retrieveBaseball(){

        baseballDTO baseballDto = baseballmapper.retrieveBaseball();

        if (baseballDto == null) {
            baseballDto = new baseballDTO();
        }

        return baseballDto;
    }

    public List<baseballDTO> retrieveBaseballist(){

        List<baseballDTO> baseballDto = baseballmapper.retrieveBaseballist();

        return baseballDto;
    }

    public HashMap insertBaseball(baseballDTO baseballDto){

        int rtnCnt = baseballmapper.insertBaseball(baseballDto);

        HashMap rtnMap = new HashMap<String, Object>();
        rtnMap.put("rtnCnt", rtnCnt);
        rtnMap.put("manageId", baseballDto.getMakeId());

        return rtnMap;
    }

    public int updateBaseball(baseballDTO baseballDto){

        int rtnCnt = baseballmapper.updateBaseball(baseballDto);

        return rtnCnt;
    }

    public int deleteBaseball(String manageId){

        int rtnCnt = baseballmapper.deleteBaseball(manageId);

        return rtnCnt;
    }
}
