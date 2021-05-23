package com.godcoder.myhome.service;

import com.godcoder.myhome.entity.contentDTO;
import com.godcoder.myhome.mapper.contentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class contentService {

    @Autowired
    contentMapper contentmapper;

    public contentDTO retrieveContent(){

        contentDTO contentdto = contentmapper.retrieveContent();

        return contentdto;
    }
}
