package com.godcoder.myhome.service;

import com.godcoder.myhome.entity.contentDTO;
import com.godcoder.myhome.mapper.contentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class contentService {

    @Autowired
    contentMapper contentmapper;

    public List<contentDTO> retrieveContents(){

        List<contentDTO> contentdto = contentmapper.retrieveContents();

        return contentdto;
    }

    public contentDTO retrieveContent(int id){

        contentDTO contentdto = contentmapper.retrieveContent(id);

        return contentdto;
    }

    public int insertContent(contentDTO contentdto) {

        int inCnt = contentmapper.insertContent(contentdto);

        return inCnt;
    }
}
