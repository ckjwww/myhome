package com.godcoder.myhome.service;

import com.godcoder.myhome.entity.contentDTO;
import com.godcoder.myhome.mapper.contentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class contentService {

    @Autowired
    contentMapper contentmapper;

    public List<contentDTO> retrieveContents(Map map){

        List<contentDTO> contentdto = contentmapper.retrieveContents(map);

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

    //컨텐츠 삭제
    public int deleteContent(int id){

        int inCnt = contentmapper.deleteContent(id);

        return inCnt;
    }

    //컨텐츠 전체건수
    public int getContentCnt(){

        int iContentCnt = contentmapper.getContentCnt();

        return iContentCnt;
    }    
}
