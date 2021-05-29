package com.godcoder.myhome.service;

import com.godcoder.myhome.entity.baseballDTO;
import com.godcoder.myhome.mapper.baseballMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class baseballService {

    @Autowired
    baseballMapper baseballmapper;

    public baseballDTO retrieveBaseball(){

        baseballDTO baseballDto = baseballmapper.retrieveBaseball();

        return baseballDto;
    }
}
